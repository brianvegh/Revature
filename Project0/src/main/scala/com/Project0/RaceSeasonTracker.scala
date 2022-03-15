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
      var choice_main=getInt(CONSTANTS.QUERIES_CASE_STRING,0,8)
      choice_main match {
        case 1=> db.queryPrintFormatted(roster_Q)
        case 2=>db.queryPrintFormatted(raceWinners_Q)
        case 3=> {
          val sR_Q=seasonRank_Q.split(";",2)
          db.execute(sR_Q(0))
          db.queryPrintFormatted(sR_Q(1))
        }
        case 4=>{
          db.queryPrintFormatted(top3EachRace_Q)
        }
        case 5=>{
          val high=getInt("Enter the HIGH position limit:")
          val low=getInt("Enter the LOW position limit:")
          db.queryPrintFormatted(percentagePositionedIndex_Q(high,low))
        }
        case 6=> db.queryPrintFormatted(finishedRaces_Q)
        case 7=> db.queryPrintFormatted(allDriverRacePositions_Q)
        case 8=> {
          val name=InputValid.getStringAlphaOnly("Enter all or part of the requested Driver's name:")
          db.queryPrintFormatted(inputedDriverRacePosition_Q(name))
        }
        case 0=> continue=false
      }
    }
  }

  def viewTables_menu(): Unit = {
    var continue:Boolean=true
    while (continue){
      var choice_main=getInt(CONSTANTS.VIEWTABLES_CASE_STRING,0,5)
      choice_main match {
        case 1=>db.queryPrintFormatted(rawTable("driver"))
        case 2=>db.queryPrintFormatted(rawTable("result"))
        case 3=>db.queryPrintFormatted(rawTable("schedule"))
        case 4=>db.queryPrintFormatted(rawTable("track"))
        case 5=>db.queryPrintFormatted(rawTable("points"))
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