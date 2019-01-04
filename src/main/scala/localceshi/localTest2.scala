package localceshi

import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat
import org.apache.hadoop.mapred.{InvalidJobConfException, JobConf}
import org.apache.hadoop.mapreduce.security.TokenCache
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext};


/**
  * 64273 pdn @create
  * 2018-12-25-11:49
  * kafkatohdfs
  *
  * 解析完之后统一的输出
  */
object localTest2 {

  case class appclass(app_info_four: String)

  case class appInfoclass(app_info_one: String, app_info_two: String, app_info_there: String, app: appclass)

  case class hardWareInfoFourClass(cpuCurFreq: String, appVersion: String, isEmulator: String, Memory: String, cpuArchitecture: String, isRooted: String, appSignatures: String,
                                   BluetoothMAC: String, Build: String, NetworkType: String, ExternalStorage: String, ethIp: String, USB: String, appName: String,
                                   cpuSerial: String, ip: String, Time: String, AndroidId: String, cpuName: String, Siminformation: String, SysFeatures: String, cpuMaxFreq: String,
                                   appPackageName: String, Display: String, cpuMinFreq: String)

  case class hardwareInfoclass(hardware_info_one: String, hardware_info_two: String, hardware_info_there: String, hardware_info_four: hardWareInfoFourClass)

  case class dataclass(app_info: appInfoclass, hardware_info: hardwareInfoclass, uid: String, device_id: String)

  def main(args: Array[String]): Unit = {
    StreamingExamples.setStreamingLogLevels()
    val sparkConf: SparkConf = new SparkConf().setAppName("kafka_riskDeviceFingerprin_toHdfs").setMaster("local[*]")
    val sparkContext = new SparkContext(sparkConf)
    sparkContext.setLogLevel("ERROR")
    val ssc = new StreamingContext(sparkContext, Seconds(10))


    val sourcetopic = "f2"
    val toHdfs = "hdfs://192.168.25.10:9000/kafkaToSpark"

    //
    val kafkaParams = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "192.168.25.10:9092,192.168.25.11:9092,192.168.25.12:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "first4",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG -> "false",
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG -> "earliest"

    )


    var messages = KafkaUtils.createDirectStream[String, String](
      ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](Array(sourcetopic), kafkaParams)
    )


    val values: DStream[String] = messages.map(_.value())

    val dataStreams = values.map(value => jsonToObject(value))


    dataStreams.foreachRDD(rdd => {
      val values: RDD[(dataclass, String)] = rdd.map(x => (x, ""))
      //rdd必须是(key,value)形式的
      RDD.rddToPairRDDFunctions(values).partitionBy(new HashPartitioner(4)).saveAsHadoopFile(toHdfs, classOf[String], classOf[String], classOf[RDDMultipleTextOutputFormat])
    })


    ssc.start()
    ssc.awaitTermination()

  }

  def jsonToObject(jsonString: String) = {
    val jsonMessage: JSONObject = JSON.parseObject(jsonString)
    val appInfoString: String = jsonMessage.getJSONObject("data").getString("app_info")
    val hardwareInfoString: String = jsonMessage.getJSONObject("data").getString("hardware_info")
    val uid: String = jsonMessage.getJSONObject("data").getString("uid")
    val device_id: String = jsonMessage.getJSONObject("data").getString("device_id")

    //    对app_info进行更加详细的解析
    val app_info_strings: Array[String] = appInfoString.split("\\|", 5)
    val app_info_one: String = app_info_strings(0)
    val app_info_two: String = app_info_strings(1)
    val app_info_there: String = app_info_strings(2)
    //    app_info的第3个字段又是一个Json串
    val app_info_four: String = app_info_strings(3)
    val app_info_four_app: String = JSON.parseObject(app_info_four).getString("app")


    //    对hardware_info的信息进行更加详细的解析
    val hardware_info_strings: Array[String] = hardwareInfoString.split("\\|", 5)
    val hardware_info_one: String = hardware_info_strings(0)
    val hardware_info_two: String = hardware_info_strings(1)
    val hardware_info_there: String = hardware_info_strings(2)
    val hardware_info_four: String = hardware_info_strings(3)


    val app = appclass(app_info_four_app)
    val app_info = appInfoclass(app_info_one, app_info_two, app_info_there, app)
    //    对应解析的是hardware_info_strings(3)字段
    var hardWareInfoFourClazz: hardWareInfoFourClass = JSON.parseObject(hardware_info_four, classOf[hardWareInfoFourClass])
    val hardware_info = hardwareInfoclass(hardware_info_one, hardware_info_two, hardware_info_there, hardWareInfoFourClazz)
    val data = dataclass(app_info, hardware_info, uid, device_id)

    data
  }


  case class RDDMultipleTextOutputFormat() extends MultipleTextOutputFormat[Any, Any] {

    val currentTime: Date = new Date()
    val formatter = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
    val dateString = formatter.format(currentTime);

    //自定义数据输出的文件名
    override def generateFileNameForKeyValue(key: Any, value: Any, name: String): String = {
      //key 和 value就是rdd中的(key,value)，name是part-00000默认的文件名
      //保存的文件名称，这里用字符串拼接系统生成的时间戳来区分文件名，可以自己定义
      "riskDeviceFingerprint" + dateString
    }

    override def checkOutputSpecs(ignored: FileSystem, job: JobConf): Unit = {
      val name: String = job.get(org.apache.hadoop.mapreduce.lib.output.FileOutputFormat.OUTDIR)
      var outDir: Path = if (name == null) null else new Path(name)
      //当输出任务不等于0 且输出的路径为空，则抛出异常
      if (outDir == null && job.getNumReduceTasks != 0) {
        throw new InvalidJobConfException("Output directory not set in JobConf.")
      }
      //当有输出任务和输出路径不为null时
      if (outDir != null) {
        val fs: FileSystem = outDir.getFileSystem(job)
        outDir = fs.makeQualified(outDir)
        outDir = new Path(job.getWorkingDirectory, outDir)
        job.set(org.apache.hadoop.mapreduce.lib.output.FileOutputFormat.OUTDIR, outDir.toString)
        TokenCache.obtainTokensForNamenodes(job.getCredentials, Array[Path](outDir), job)
        //下面的注释掉，就不会出现这个目录已经存在的提示了
        /* if (fs.exists(outDir)) {
             throw new FileAlreadyExistsException("Outputdirectory"
                     + outDir + "alreadyexists");
         }
      }*/
      }
    }
  }

}