import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf




case class JsonData(country:String,
                        id: BigInt, 
                        points: BigInt, 
                        price: Option[Double], 
                        title: String, 
                        variety: String, 
                        winery: String)

object JsonReader extends App {
    def main(path: String) : Unit = {
        val conf = new SparkConf().setAppName("JsonReader").setMaster("local")
        val sc = new SparkContext(conf)
        
        //val path: String = "file:///data/winemag-data-130k-v2.json"
        val json = sc.textFile(path)
        implicit val formats = DefaultFormats
		
		println("testsss ---------->")
		
        val result = json.map(s => parse(s).extract[JsonData])
        result.take(30).foreach(println)
			//(line => println(line.toString))
            //result.take(n).foreach(println)
    }
}




