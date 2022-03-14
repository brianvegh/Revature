package com.Project0

import java.sql.{Connection, DriverManager, ResultSet, SQLException, Statement}

class SQLbridge(val driver:String, val url:String, val username:String, val password:String) {
  private var connection:Connection = null
  private var statement:Statement = null
  private var r:ResultSet =null

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

  def query(query:String): ResultSet= {
    try {
     statement = connection.createStatement()
     val rs:ResultSet=statement.executeQuery(query)
    } catch {
      case e:SQLException=>{
        e.printStackTrace()

      }
    }

  }

  def queryPrintAsCsv(rs: ResultSet): Unit={
    var rsMetaData= rs.getMetaData()
    for(i<-1 until rsMetaData.getColumnCount) {
      print(rsMetaData.getColumnName(i))
      if ((i+1)!=rsMetaData.getColumnCount) print(", ")
    }
    println()
    while ( rs.next() ) {
      println(rs.getString(1)+", " +rs.getString(2) +", " +rs.getString(3))
    }
  }
  def queryPrintAsCsv(q:String):Unit={
    queryPrintAsCsv(query(q))
  }
  def queryToCsv(rs:ResultSet, filepath:String):Unit= {

  }


}
