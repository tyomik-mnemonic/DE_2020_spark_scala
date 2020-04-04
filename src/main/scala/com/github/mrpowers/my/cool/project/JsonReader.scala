package com.github.mrpowers.my.cool.project

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.read



case class JsonData(
                    id: Option[BigInt], 
                    country: Option[String],
                    points: Option[BigInt], 
                    price: Option[Double], 
                    title: Option[String], 
                    variety: Option[String], 
                    winery: Option[String])

object JsonReader{
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("JsonReader").setMaster("local")
        val sc = new SparkContext(conf);
        
        val path = args(0)
        val json = sc.textFile(path)
        implicit val formats = DefaultFormats
						
        val result = json.map{s => implicit val formats = DefaultFormats; parse(s).extract[JsonData]}
        result.map(println).collect
     
    }
}




///opt/apache-spark/bin/spark-submit --master "local[2]" --class main.scala.com.github.mrpowers.my.cool.project.JsonReader  target/scala-2.11/json_reader_komarov-assembly-0.0.1.jar "data/winemag-data-130k-v2.json"
