package com.Project0

import java.sql.{Connection, DriverManager, ResultSet, SQLException, Statement}
import scala.collection.mutable.ListBuffer
import scala.collection.immutable.StringOps

class SQLbridge(val driver:String, val url:String, val username:String, val password:String) {


  private var connection:Connection = null
  private var statement:Statement = null
  private var r:ResultSet =null


  def queryToCsv(rs:ResultSet, filepath:String):Unit= {

  }

  def execute(str: String): Int = statement.executeUpdate(str)

  //noinspection DuplicatedCode
  def batchExecute(strings: Array[String]): Unit ={
    for (s <- 1 until (strings.length - 1)) {
      statement.executeUpdate(strings(s))
    }
  }

  def batchExecuteQuery(strings: Array[String]): ResultSet = {
    for (s <- 0 until (strings.length - 2)) {
      query(strings(s))
    }
    query(strings(strings.length - 1))
  }

  def query(query:String): ResultSet= {
    try {
      statement = connection.createStatement()
      statement.executeQuery(query)
    } catch {
      case e:SQLException=>{
        e.printStackTrace()
        null;
      }
    }
  }

  def queryPrintAsCsv(q:String):Unit={
    queryPrintAsCsv(query(q))
  }
  def queryPrintAsCsv(rs: ResultSet): Unit={
    var rsMetaData= rs.getMetaData()
    val columnCount=rsMetaData.getColumnCount
    for(i<- 1 until columnCount) {
      print(rsMetaData.getColumnLabel(i))
      if ((i)!=columnCount) print(", ")
    }
    print(rsMetaData.getColumnName(columnCount))
    println()
    while ( rs.next() ) {
      for(i<- 1 until columnCount) {
        print(rs.getString(i))
        if ((i)!=columnCount) print(", ")
      }
      print(rs.getString(columnCount))
      print("\n")
  }
    System.out.flush();readLine();System.out.flush()
  }

  def queryPrintFormatted(q:String):Unit={
    queryPrintFormatted(query(q))
  }
  def queryPrintFormatted(rs: ResultSet): Unit={
    var rsMetaData= rs.getMetaData()
    val columnCount:Int=rsMetaData.getColumnCount
    val tokens:ListBuffer[ListBuffer[String]]=new ListBuffer[ListBuffer[String]]
    val columnWidth:ListBuffer[Int] = new ListBuffer[Int]
    for(i<- 1 until columnCount+1) {
      columnWidth.append(rsMetaData.getColumnLabel(i).length)
      tokens.append(new ListBuffer[String])
      tokens(i-1).append(rsMetaData.getColumnLabel(i))
    }
    while (rs.next() ) {
      for(i<- 1 until columnCount+1) {
       if (rs.getString(i).length>columnWidth(i-1)) {
        columnWidth(i-1)=rs.getString(i).length
        }
        tokens(i-1).append(rs.getString(i))
      }
    }
    for (i<-tokens(0).indices){
      for (j<-(tokens).indices){
        val string:String=tokens(j)(i)
        val width:Int=columnWidth(j)+3
        val stringFormatted:String=string.padTo(width," ").mkString
        print(stringFormatted)
      }
      print("\n")
    }


    System.out.flush();readLine();System.out.flush()
}

  def connect(): Unit = {
    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
    } catch {
      case e: Throwable => e.printStackTrace()
    }
  }

  def disconnect(): Unit = {
    connection.close()
    statement.close()
    r.close()
  }
}
