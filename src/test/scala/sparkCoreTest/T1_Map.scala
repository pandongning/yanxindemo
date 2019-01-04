package sparkCoreTest

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext};

/**
  * 64273 pdn @create 
  * 2019-01-04-16:43
  * mysparkcode
  *sparkStreaming.transformation
  */
object T1_Map {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName("kafka_riskDeviceFingerprin_toHdfs").setMaster("local[*]")
    val sparkContext = new SparkContext(sparkConf)
    sparkContext.setLogLevel("ERROR")
    val ssc = new StreamingContext(sparkContext, Seconds(10))

    val sourcetopic = "f1"

    //kafka消费者的参数。现在是测试阶段则设置为不自动的提交偏移量和每次从最开始的位置去读取数据
    val kafkaParams = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "192.168.25.10:9092,192.168.25.11:9092,192.168.25.12:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "v1",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
      ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG -> (false: java.lang.Boolean),
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG -> "earliest"
    )

    //    对于LocationStrategies和ConsumerStrategies有许多不同的配置，具体的去看官方的文档



    val messages = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Array(sourcetopic), kafkaParams))


    // Get the lines, split them into words, count the words and print
    val lines = messages.map(_.value)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
    wordCounts.print()

    // Start the computation
    ssc.start()
    ssc.awaitTermination()



  }

}
