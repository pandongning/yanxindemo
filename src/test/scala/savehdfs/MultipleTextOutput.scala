import java.text.SimpleDateFormat
import java.util.Date

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat
import org.apache.hadoop.mapred.{InvalidJobConfException, JobConf}
import org.apache.hadoop.mapreduce.security.TokenCache
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.apache.spark.rdd.RDD;


/**
  * 在HDFS目录下继续追加文件，不会覆盖以前的文件
  * Created by csw on 2017/6/23.
  */
object MultipleTextOutput {
  def main(args: Array[String]) {
    val filePath = "hdfs://master:9000/csw/tmp/data";
    val savePath = "hdfs://master:9000/hxzj/mydata/tatol";
    val conf = new SparkConf().setAppName("Spark shell")
    val sc = new SparkContext(conf)
    //读取文件后，不进行split操作，直接将整行内容看作key,
    val rdd: RDD[(String, String)] = sc.textFile(filePath).map(x => (x, ""))
    //rdd必须是(key,value)形式的
    RDD.rddToPairRDDFunctions(rdd).partitionBy(new HashPartitioner(1)).saveAsHadoopFile(savePath, classOf[String], classOf[String], classOf[RDDMultipleTextOutputFormat])
    sc.stop()
  }

  /**
    * 自定义一个输出文件类
    */
  case class RDDMultipleTextOutputFormat() extends MultipleTextOutputFormat[Any, Any] {

    val currentTime: Date = new Date()
    val formatter = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
    val dateString = formatter.format(currentTime);

    //自定义保存文件名
    override def generateFileNameForKeyValue(key: Any, value: Any, name: String): String = {
      //key 和 value就是rdd中的(key,value)，name是part-00000默认的文件名
      //保存的文件名称，这里用字符串拼接系统生成的时间戳来区分文件名，可以自己定义
      "HTLXYFY" + dateString
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
