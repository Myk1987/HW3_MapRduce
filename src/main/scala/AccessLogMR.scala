/**
 * Created by Sergey_Moukavoztchik on 10/2/2015.
 */

import java.io.IOException
import java.util.Iterator

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs._
import org.apache.hadoop.io._
import org.apache.hadoop.mapred._



object AccessLogMR {






  def main(args: Array[String]) = {
    val conf = new JobConf(classOf[Map])
    conf setJobName "wordCount"

    conf setOutputKeyClass classOf[Text]
    conf setOutputValueClass classOf[IntWritable]

    conf setMapperClass classOf[Map]
    conf setCombinerClass classOf[Reduce]

    conf setReducerClass classOf[Reduce]

    conf setInputFormat classOf[TextInputFormat]

    conf setOutputFormat classOf[TextOutputFormat[_ <: WritableComparable, _ <: Writable]]

    conf setInputPath(args(0))
    conf setOutputPath(args(1))

    JobClient runJob conf
  }

  public static void main(String[] args) throws Exception {
    JobConf conf = new JobConf(WordCount.class);
    conf.setJobName("wordcount");


    conf.setOutputKeyClass(Text.class);
    conf.setOutputValueClass(IntWritable.class);


    conf.setMapperClass(WordCountScala.class);
    conf.setCombinerClass(Reduce.class);
    conf.setReducerClass(Reduce.class);


    conf.setInputFormat(TextInputFormat.class);
    conf.setOutputFormat(TextOutputFormat.class);


    FileInputFormat.setInputPaths(conf, new Path(args[0]));
    FileOutputFormat.setOutputPath(conf, new Path(args[1]));


    JobClient.runJob(conf);
  }

  // Create a new JobConf
  JobConf job = new JobConf(new Configuration(), MyJob.class);

  // Specify various job-specific parameters
  job.setJobName("myjob");

  FileInputFormat.setInputPaths(job, new Path("in"));
  FileOutputFormat.setOutputPath(job, new Path("out"));

  job.setMapperClass(MyJob.MyMapper.class);
  job.setCombinerClass(MyJob.MyReducer.class);
  job.setReducerClass(MyJob.MyReducer.class);

  job.setInputFormat(SequenceFileInputFormat.class);
  job.setOutputFormat(SequenceFileOutputFormat.class);
}
