package com.Project1
import com.Project1.InputValid.{getInt, getString}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions._

import java.util.regex.Pattern

case class User (userame:String, firstName:String, lastName :String, isAdmin:Boolean,createSparkSession:Boolean=true) {}
case class SessionID (sessionID:Int)

class UserSession (user: User,sessionID: SessionID) extends Serializable {
  System.setProperty("hadoop.home.dir", "C:\\hadoop")
  lazy val getUser:User=user
  lazy val getSessionID:Int=sessionID.sessionID
  lazy val getUsername:String=user.userame
  Logger.getLogger("org").setLevel(Level.OFF) //TURN OFF LOG4J Logs in Console
  Logger.getLogger("akka").setLevel(Level.OFF)
  val conf = new SparkConf().set("spark.executor.memory", "8g").set("spark.executor.cores", "4")
  val spark= {
    SparkSession
      .builder
      .appName("hello hive")
      .config("spark.master", "local")
      //.config(conf)
      .enableHiveSupport()
      .getOrCreate()
  }

  if (user.createSparkSession) {
    spark.sparkContext.setLogLevel("OFF")
  }


  def closeSpark():Unit={ spark.close()  }


  import spark.implicits._
  //import car brands into spark
  val BRANDS = spark.read.csv("hdfs://localhost:9000/user/hive/warehouse/BRANDS.csv")
  //import new data from hfds .csv file to dataframe
  var ad = spark.read.option("header", true).csv("hdfs://localhost:9000/user/hive/warehouse/ALLDATA.csv").distinct()
  //remove any extra Header Rows from merge
  var temp= ad.where("Title_URL !='Title_URL' and Date != 'Date'")
  ad = temp

  //price column mod, as df colPrice
  val priceMod: String => String = _.replaceAll("[$,]", "")
  val priceModUDF = udf(priceMod)
  spark.udf.register("priceModUDF", priceMod)
  temp = ad.withColumn("Prices", priceModUDF('Price))
  ad = temp

  //date Mod code
  import java.util.Calendar
  val YYYY: String = Calendar.getInstance().get(Calendar.YEAR).toString()
  def dateMod(s: String): String = {
    var a: Array[String] = s.split(" +")
    val DD = a(1)
    val MM: String = {
      a(0) match {
        case "Jan" => "01"
        case "Feb" => "02"
        case "Mar" => "03"
        case "Apr" => "04"
        case "May" => "05"
        case "Jun" => "06"
        case "Jul" => "07"
        case "Aug" => "08"
        case "Sep" => "09"
        case "Oct" => "10"
        case "Nov" => "11"
        case "Dec" => "12"
      }
    }
    s"$YYYY-$MM-$DD"
  }
  val dateModUDF = udf(dateMod _)
  spark.udf.register("dateModUDF", dateMod _)
  temp = ad.withColumn("Date", dateModUDF('Date))
  ad = temp

  //get brand from Title
  var x= BRANDS.collect.map(_.toSeq).flatten
  var y=x.map(_.toString.toLowerCase)
  def getBrand(title:String):String = {
    var brand=""
    y.foreach((s: String) => if (title.toLowerCase.contains(s)) {
      brand = s.split(' ').map(_.capitalize).mkString(" ")
    }
    )
    brand
  }
  val getBrandUDF=udf(getBrand _)
  spark.udf.register("getBrandUDF", getBrand _)
  temp=ad.withColumn("Brand", getBrandUDF('Title))
  ad = temp

  //get year from title
  def getYear(title:String):String = {
    var year=""
    val p = Pattern.compile("[0-9]{4}")
    val m = p.matcher(title)
    if (m.find) {
      year=(m.group)
    }
    year
  }
  val getYearUDF=udf(getYear _)
  spark.udf.register("getYearUDF", getYear _)
  temp=ad.withColumn("Year", getYearUDF('Title))
  ad = temp

  //get city from URL
  def getCity(url:String):String = {
    url.substring(8,(url.indexOf('.')))
  }
  val getCityUDF=udf(getCity _)
  spark.udf.register("getCityUDF", getCity _)
  temp=ad.withColumn("City_Code", getCityUDF('Title_URL))
  ad = temp

  var almost = ad.withColumnRenamed("City_Code","City").withColumnRenamed("" +
    "Title_URL","URL").withColumnRenamed("Image","ImageURL")
  var stagedUpateDF=almost.select("Date","Brand","Year","Prices","City", "Title","URL","ImageURL")
  var df=stagedUpateDF


  def q_four(): Unit = {
    var df=stagedUpateDF
    df.groupBy("Brand").count().show(100)
  }

  def q_three(): Unit = {
    //BRANDS.show(100)
    val year =getInt("Please enter the year for which you would like the Brands Average Prices",1912,2022)
    var df=stagedUpateDF
    df.filter(df("Year") === year).groupBy("Brand").agg(avg("Prices")).show(100)
  }

  def q_two(): Unit = {
    //var df=stagedUpateDF
    df.groupBy("Brand").agg(avg("Year").as("avgYear")).select("Brand", "avgYear").show(100)
  }

  def q_one(): Unit = {
    updateSparkDB
  }

  def q_five(): Unit = {
    var df=stagedUpateDF
    var hasImage=df.select("ImageURL").distinct().count().toDouble
    var totalRecords=df.count().toDouble
    var percent=hasImage/totalRecords
    println(s"There are $totalRecords records in the database. $hasImage records have an image (image url)")
    println(s"$percent percent of posts in database have an image")
  }


//  //spark.sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING) USING hive")
//  //spark.sql("CREATE TABLE IF NOT EXISTS src(key INT, value STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ‘,’ STORED AS TEXTFILE")
//  //spark.sql("LOAD DATA LOCAL INPATH 'input/kv1.txt' INTO TABLE src")
//  //spark.sql("CREATE TABLE IF NOT EXISTS src (key INT,value STRING) USING hive")
//  spark.sql("create table IF NOT EXISTS newone(id Int,name String) row format delimited fields terminated by ','");
//  spark.sql("LOAD DATA LOCAL INPATH 'kv1.txt' INTO TABLE newone")
//  spark.sql("SELECT * FROM newone").show()

}
