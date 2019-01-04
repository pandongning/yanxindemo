package liwei



import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, KafkaUtils}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object twoSourceJoin {

  def main(args: Array[String]): Unit = {
    val group = "g1"
    val topic = "test"
    val topic1 = "test1"
    //创建SparkConf，如果将任务提交到集群中，那么要去掉.setMaster("local[2]")
    val conf = new SparkConf().setAppName("DirectStream").setMaster("local[*]")
    //创建一个StreamingContext，其里面包含了一个SparkContext
    val streamingContext = new StreamingContext(conf, Seconds(2));

    //配置kafka的参数
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "127.0.0.1:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> group,
      "auto.offset.reset" -> "latest", // latest earliest
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array(topic, topic1)
    //在Kafka中记录读取偏移量
    val stream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      //位置策略
      PreferConsistent,
      //订阅的策略
      Subscribe[String, String](topics, kafkaParams)
    )

    //迭代DStream中的RDD，将每一个时间点对于的RDD拿出来

    stream.foreachRDD { rdd =>
      //获取该RDD对于的偏移量
      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

      // 收集主题test的信息。
      rdd.cache()
      //        val rdds = rdd.persist(StorageLevel.DISK_ONLY)
      val infoTest: RDD[ConsumerRecord[String, String]] = rdd.filter(i => i.topic() == "test")
      val testMap: RDD[(String, String)] = infoTest.map(i => i.value()).map(i => {
        val lines: Array[String] = i.split(",")
        val user = lines(0)
        (user, i)
      })
      testMap.cache()

      // 收集主题test1的信息。
      val infoTest1: RDD[ConsumerRecord[String, String]] = rdd.filter(i => i.topic() == "test1")
      val test1Map: RDD[(String, String)] = infoTest1.map(i => i.value()).map(i => {
        val lines: Array[String] = i.split(",")
        val user = lines(0)
        (user, i)
      })
      test1Map.cache()


      val allInfo: RDD[(String, (String, String))] = testMap.join(test1Map)
      val result: RDD[(String, String, String, String, String, String, String, String, String, String)] = allInfo.map(i => {
        val user = i._1
        val row = (i._2).toString()
        val rowArr = row.split(",")
        (user, rowArr(1), rowArr(2), rowArr(3), rowArr(4), rowArr(6), rowArr(7), rowArr(8), rowArr(9), rowArr(10))
      })


      result.foreach(println(_))


      //更新偏移量
      // some time later, after outputs have completed(将偏移量更新【Kafka】)
      stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    }

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}