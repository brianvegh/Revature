package com.Project0

import java.sql.{Connection, DriverManager, PreparedStatement, SQLException, Statement}

object RaceSeasonTracker {
  val driver = "com.mysql.cj.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/racing"
  val username = "root"
  val password = "Generic1!"
  var connection: Connection = null
  val statement: Statement = null


  def main(args: Array[String]) {
    val db = new SQLbridge(driver, url, username, password)
    db.connect()
    db.queryPrintAsCsv("SELECT * FROM Driver;")


  }
}
//source:
//https://alvinalexander.com/scala/scala-jdbc-connection-mysql-sql-select-example/