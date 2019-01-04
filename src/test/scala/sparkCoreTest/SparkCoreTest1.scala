package sparkCoreTest

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkCoreTest1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("aa").setMaster("local[*]")
    val sparkContext = new SparkContext(conf)
    val rdd: RDD[String] = sparkContext.textFile("hdfs://192.168.25.10:9000/kafkaToSpark")
    val rdd1 = rdd.repartition(1)
    rdd1.saveAsTextFile("hdfs://192.168.25.10:9000/pdn")
    println(rdd1.partitions.size)//2
    sparkContext.stop()
  }

}
