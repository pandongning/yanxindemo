package multipleOutputsTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import java.net.URI;

/**
 * 64273 pdn @create
 * 2019-01-03-11:13
 * kafkatohdfs
 * multipleOutputsTest
 */

public class MultipleOutputsTest {
    // 定义输入路径
    private static final String INPUT_PATH = "hdfs://liaozhongmin:9000/multipleOutput/multipleOutput_data";
    // 定义输出路径
    private static final String OUT_PATH = "hdfs://liaozhongmin:9000/out";

    public static void main(String[] args) {

        try {
            // 创建配置信息
            Configuration conf = new Configuration();


            // 创建文件系统
            FileSystem fileSystem = FileSystem.get(new URI(OUT_PATH), conf);
            // 如果输出目录存在，我们就删除
            if (fileSystem.exists(new Path(OUT_PATH))) {
                fileSystem.delete(new Path(OUT_PATH), true);
            }

            // 创建任务
            Job job = new Job(conf, MultipleOutputsTest.class.getName());

            //1.1	设置输入目录和设置输入数据格式化的类
            FileInputFormat.setInputPaths(job, INPUT_PATH);
            job.setInputFormatClass(TextInputFormat.class);

            //1.2	设置自定义Mapper类和设置map函数输出数据的key和value的类型
            job.setMapperClass(MultipleOutputsMapper.class);
            job.setMapOutputKeyClass(NullWritable.class);
            job.setMapOutputValueClass(Text.class);

            //1.3	设置分区和reduce数量(reduce的数量，和分区的数量对应)
            job.setPartitionerClass(HashPartitioner.class);
            job.setNumReduceTasks(0);

            //1.4	排序
            //1.5	归约
            //2.1	Shuffle把数据从Map端拷贝到Reduce端。
            //2.2	指定Reducer类和输出key和value的类型
            //job.setReducerClass(MyReducer.class);
            job.setOutputKeyClass(NullWritable.class);
            job.setOutputValueClass(Text.class);

            //2.3	指定输出的路径和设置输出的格式化类
            FileOutputFormat.setOutputPath(job, new Path(OUT_PATH));
            job.setOutputFormatClass(TextOutputFormat.class);


            // 提交作业 退出
            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}