package org.example

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import java.util.Properties

object Job {
  def main(args: Array[String]) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val serdeSchema = new SimpleStringSchema()
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "ec2-34-203-160-240.compute-1.amazonaws.com:9092")
    properties.setProperty("zookeeper.connect", "ec2-34-203-160-240.compute-1.amazonaws.com:2181")
    properties.setProperty("group.id", "test")
    val stream = env.addSource(new FlinkKafkaConsumer010[String]("test", serdeSchema, properties)).print()

    env.execute("Flink Kafka Example")
  }
}