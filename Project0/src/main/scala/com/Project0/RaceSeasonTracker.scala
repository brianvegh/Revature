package com.Project0

import java.sql.{Connection, DriverManager, PreparedStatement, SQLException, Statement}

protected object RaceSeasonTracker {
  val dbName="racing"
  val dbHost="localhost:3306"
  val driver = "com.mysql.cj.jdbc.Driver"
  val url = s"jdbc:mysql://$dbHost/$dbName"
  val username = "root"
  val password = "Generic1!"
  var connection: Connection = null
  val statement: Statement = null
  val db = new SQLbridge(driver, url, username, password)

  def main(args: Array[String]) {
    var continue:Boolean=true
    db.connect()
    CONSTANTS.WELCOME_MESSAGE_PRINT
    CONSTANTS.DB_CONNECTED_PRINT(dbName,dbHost)
    while (continue){


    }//main while loop
  }
}
//source:
//https://alvinalexander.com/scala/scala-jdbc-connection-mysql-sql-select-example/