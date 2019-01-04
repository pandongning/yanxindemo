package multipleOutputsTest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * 64273 pdn @create
 * 2019-01-03-11:31
 * kafkatohdfs
 * multipleOutputsTest
 */
public class MultipleOutputsMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
    //定义MultipleOutputs
    private MultipleOutputs<NullWritable, Text> multipleOutputs = null;

    @Override
    protected void setup(Mapper<LongWritable, Text, NullWritable, Text>.Context context) throws IOException, InterruptedException {
        //初始化MultipleOutputs对象
        multipleOutputs = new MultipleOutputs<NullWritable, Text>(context);
    }

    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, NullWritable, Text>.Context context) throws IOException,
            InterruptedException {
        //把结果直接写出去(注意这里不是context而是MultipleOutps的对象)
        multipleOutputs.write(NullWritable.get(), value, generateFileName(value));
    }

    /**
     * 自定义一个产生文件名的方法
     *
     * @param value
     * @return
     */
    private String generateFileName(Text value) {
        //对字符串进行切分
        String[] splits = value.toString().split(",");
        //截取国家的字符串
        String country = splits[4].substring(1, 3);

        //返回一个包含国家的文件名
        return country + "/";
    }

    /**
     * 用完MultipleOutputs之后一定要关闭
     */
    @Override
    protected void cleanup(Mapper<LongWritable, Text, NullWritable, Text>.Context context) throws IOException, InterruptedException {
        multipleOutputs.close();
    }
}
