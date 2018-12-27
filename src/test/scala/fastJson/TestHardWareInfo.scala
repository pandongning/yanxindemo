package fastJson

import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 64273 pdn @create 
  * 2018-12-26-20:53
  * kafkatohdfs
  * fastJson
  */
object TestHardWareInfo {

  case class hardWareInfoFourClass(cpuCurFreq: String, appVersion: String, isEmulator: String, Memory: String, cpuArchitecture: String, isRooted: String, appSignatures: String,
                                   BluetoothMAC: String, Build: String, NetworkType: String, ExternalStorage: String, ethIp: String, USB: String, appName: String,
                                   cpuSerial: String, ip: String, Time: String, AndroidId: String, cpuName: String, Siminformation: String, SysFeatures: String, cpuMaxFreq: String,
                                   appPackageName: String, Display: String, cpuMinFreq: String)

  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName("aa").setMaster("local[*]")
    val sparkContext = new SparkContext(sparkConf)
    val data = sparkContext.parallelize(Array((30,"hadoop"), (71,"hive"), (11,"cat")))



  }

}
