package com.Project1

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions.{col, udf}

import java.util.regex.Pattern

object updateSparkDB extends Serializable {
    val spark= {
      SparkSession
        .builder
        .appName("hello hive")
        .config("spark.master", "local")
        .enableHiveSupport()
        .getOrCreate()
    }
  
    import spark.implicits._
    //import car brands into spark
    val BRANDS = spark.read.csv("hdfs://localhost:9000/user/hive/warehouse/BRANDS.csv")
    //import new data from hfds .csv file to dataframe
    var ad = spark.read.option("header", true).csv("hdfs://localhost:9000/user/hive/warehouse/ALLDATA.csv").distinct()
    //remove any extra Header Rows from merge
    var temp= ad.where("Title_URL !='Title_URL' and Date != 'Date'")
    ad = temp

    //price column mod, as df colPrice
    val priceMod: String => Int = _.replaceAll("[$,]", "").toInt
    val priceModUDF = udf(priceMod)
    spark.udf.register("priceModUDF", priceMod)
    temp = ad.withColumn("Price", priceModUDF('Price))
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



  val almost = ad.withColumnRenamed("City_Code","City").withColumnRenamed("" +
    "Title_URL","URL").withColumnRenamed("Image","ImageURL")
  val stagedUpateDF=almost.select("Date","Brand","Year","Price","City", "Title","URL","ImageURL")
  //stagedUpateDF.write.option("header",true).csv("/tmp/spark_output/datacsv")
    //join ad on current db and save partitioned and bucketed
//  stagedUpateDF.write.partitionBy("Brand").bucketBy(12, "Date").format("parquet").save("CraigslistSparkDB.parquet")
//  stagedUpateDF.write.partitionBy("Brand").format("parquet").save("CraigslistSparkDB.parquet")




}
