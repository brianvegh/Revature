package com.Project0

import java.sql.{Connection, DriverManager, PreparedStatement, SQLException, Statement}
import InputValid._
import QUERIES_SQL._

object RaceSeasonTracker {
  val dbName="racing"
  val dbHost="localhost:3306"
  val driver = "com.mysql.cj.jdbc.Driver"
  val url = s"jdbc:mysql://$dbHost/$dbName"
  val username = "root"
  val password = "Generic1!"
  val db = new SQLbridge(driver, url, username, password)

  def queries_menu(): Unit = {
    var continue:Boolean=true
    while (continue){
      var choice_main=getInt(CONSTANTS.QUERIES_CASE_STRING,0,6)
      choice_main match {
        case 1=> db.queryPrintAsCsv(roster_Q)
        case 2=>db.queryPrintAsCsv(raceWinners_Q)
        case 3=> {
          val sR_Q=seasonRank_Q.split(";",2)
          db.execute(sR_Q(0))
          db.queryPrintAsCsv(sR_Q(1))
        }
        case 4=>{
          db.queryPrintAsCsv(top3EachRace_Q)
        }
        case 5=>{
          var high=getInt("Enter the HIGH position limit:")
          var low=getInt("Enter the LOW position limit:")
          db.queryPrintAsCsv(percentagePositionedIndex_Q(high,low))
        }
        case 6=> db.queryPrintAsCsv(finishedRaces_Q)
        case 0=> continue=false
      }
    }
  }

  def viewTables_menu(): Unit = {
    var continue:Boolean=true
    while (continue){
      var choice_main=getInt(CONSTANTS.VIEWTABLES_CASE_STRING,0,5)
      choice_main match {
        case 1=>
        case 2=>
        case 3=>
        case 4=>
        case 5=>
        case 0=> continue=false
      }
    }
  }

  def updateTables_menu(): Unit = {
    var continue:Boolean=true
    while (continue){
      println()
      var choice_main=getInt(CONSTANTS.UPDATETABLES_CASE_STRING,0,6)
      choice_main match {
        case 1=>
        case 2=>
        case 3=>
        case 4=>
        case 5=>
        case 6=>
        case 0=> continue=false
      }
    }
  }

  def main(args: Array[String]) {
    db.connect()
    CONSTANTS.WELCOME_MESSAGE_PRINT()
    CONSTANTS.DB_CONNECTED_PRINT(dbName,dbHost)
    var continue:Boolean=true
    while (continue){
      println()
      db.queryPrintFormatted(roster_Q)
      var choice_main=getInt(CONSTANTS.MAIN_CASE_STRING,0,3)
      choice_main match {
        case 1=> queries_menu()
        case 2=> viewTables_menu()
        case 3=> updateTables_menu()
        case 0=> continue=false
      }
    }//main while loop
    db.disconnect()
    CONSTANTS.DB_DISCONNECTED_PRINT(dbName,dbHost)
  }
}
//source:
//https://alvinalexander.com/scala/scala-jdbc-connection-mysql-sql-select-example/