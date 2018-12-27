import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 64273 pdn @create
  * 2018-12-25-11:49
  * kafkatohdfs
  *
  */
object KafkaToHdfs {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName("kafka_riskDeviceFingerprin_toHdfs").setMaster("local[*]")
    val sparkContext = new SparkContext(sparkConf)
    sparkContext.setLogLevel("ERROR")
    val ssc = new StreamingContext(sparkContext, Seconds(20))


    val sourcetopic = "riskDeviceFingerprint"
    val toHdfs = "hdfs://192.168.25.10:9000/kafkaToSpark"

    // ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "192.168.25.10:9092,192.168.25.11:9092,192.168.25.12:9092",
    val kafkaParams = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "192.168.25.10:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "first4",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG -> "false",
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG -> "earliest"

    )


    var messages = KafkaUtils.createDirectStream[String, String](
      ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](Array(sourcetopic), kafkaParams)
    )


    val lines = messages.map(_.value)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
    wordCounts.print()




    lines.foreachRDD(rdd => {
      rdd.saveAsTextFile(toHdfs)
      Thread.sleep(10000)
    })


  /*  lines.foreachRDD(rdd => {
      rdd.foreachPartition(partitionOfRecords =>{
        partitionOfRecords
      })
    })*/

//    lines.saveAsTextFiles(toHdfs,"txt")

    // Start the computation
    ssc.start()
    ssc.awaitTermination()


  }

}
