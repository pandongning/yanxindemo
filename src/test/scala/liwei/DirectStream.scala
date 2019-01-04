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
/**
  * 64273 pdn @create 
  * 2019-01-04-10:22
  * kafkatohdfs
  * liwei
  */

object DirectStream {

  def main(args: Array[String]): Unit = {
    //    StreamingExamples.setStreamingLogLevels()
    val group = "g1"
    val topic = "test111"
    //创建SparkConf，如果将任务提交到集群中，那么要去掉.setMaster("local[2]")
    val conf = new SparkConf().setAppName("DirectStream").setMaster("local[*]")
    //创建一个StreamingContext，其里面包含了一个SparkContext
    val streamingContext = new StreamingContext(conf, Seconds(2));

    //配置kafka的参数
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "master:9092,slave:9092,slave1:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> group,
      "auto.offset.reset" -> "latest", // latest earliest
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array(topic)
    //在Kafka中记录读取偏移量
    val stream = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      //位置策略
      PreferConsistent,
      //订阅的策略
      Subscribe[String, String](topics, kafkaParams)
    )


    //    //直连方式只有在KafkaDStream的RDD中才能获取偏移量，那么就不能到调用DStream的Transformation
    //    //所以只能在kafkaStream调用foreachRDD，获取RDD的偏移量，然后就是对RDD进行操作了
    //    //依次迭代KafkaDStream中的KafkaRDD
    //    kafkaStream.foreachRDD { kafkaRDD =>
    //      //只有KafkaRDD可以强转成HasOffsetRanges，并获取到偏移量
    //      offsetRanges = kafkaRDD.asInstanceOf[HasOffsetRanges].offsetRanges
    //      val lines: RDD[String] = kafkaRDD.map(_._2)
    //
    //      //对RDD进行操作，触发Action
    //      lines.foreachPartition(partition =>
    //        partition.foreach(x => {
    //          println(x)
    //        })
    //      )


    //迭代DStream中的RDD，将每一个时间点对于的RDD拿出来
    stream.foreachRDD { rdd =>
      //获取该RDD对于的偏移量
      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

      val values: RDD[String] = rdd.map(i=>i.value())


      //      info.foreach(println(_))
      //      val result1: RDD[(Int, String)] = info.map(i =>(1, i._3)).reduceByKey(_+_)
      //      result1.foreach(println(_))

      //更新偏移量
      // some time later, after outputs have completed(将偏移量更新【Kafka】)
      stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    }

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}