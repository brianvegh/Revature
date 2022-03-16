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
  val dbBridge = new SQLbridge(driver, url, username, password, dbName)


  def queries_menu(): Unit = {
    var continue:Boolean=true
    while (continue){
      var choice_main=getInt(CONSTANTS.QUERIES_CASE_STRING,0,8)
      choice_main match {
        case 1=> dbBridge.queryPrintFormatted(roster_Q)
        case 2=>dbBridge.queryPrintFormatted(raceWinners_Q)
        case 3=> {
          val sR_Q=seasonRank_Q.split(";",2)
          dbBridge.execute(sR_Q(0))
          dbBridge.queryPrintFormatted(sR_Q(1))
        }
        case 4=>{
          dbBridge.queryPrintFormatted(top3EachRace_Q)
        }
        case 5=>{
          val high=getInt("Enter the HIGH position limit:")
          val low=getInt("Enter the LOW position limit:")
          dbBridge.queryPrintFormatted(percentagePositionedIndex_Q(high,low))
        }
        case 6=> dbBridge.queryPrintFormatted(finishedRaces_Q)
        case 7=> dbBridge.queryPrintFormatted(allDriverRacePositions_Q)
        case 8=> {
          val name=InputValid.getStringAlphaOnly("Enter all or part of the requested Driver's name:")
          dbBridge.queryPrintFormatted(inputedDriverRacePosition_Q(name))
        }
        case 0=> continue=false
      }
    }
  }

  def viewTables_menu(): Unit = {
    var continue:Boolean=true
    while (continue){
      var choice_main=getInt(CONSTANTS.VIEWTABLES_CASE_STRING,0,55)
      choice_main match {
        case 1=>dbBridge.queryPrintFormatted(rawTable("driver"))
        case 2=>dbBridge.queryPrintFormatted(rawTable("result"))
        case 3=>dbBridge.queryPrintFormatted(rawTable("schedule"))
        case 4=>dbBridge.queryPrintFormatted(rawTable("track"))
        case 5=>dbBridge.queryPrintFormatted(rawTable("points"))
        case 11=>dbBridge.queryToCsvFile(rawTable("driver"))
        case 22=>dbBridge.queryToCsvFile(rawTable("result"))
        case 33=>dbBridge.queryToCsvFile(rawTable("schedule"))
        case 44=>dbBridge.queryToCsvFile(rawTable("track"))
        case 55=>dbBridge.queryToCsvFile(rawTable("points"))
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
        case 1=> importResultFromCSV()
        case 2=>dbBridge.execute(addRaceToSchedule_U())
        case 3=>dbBridge.execute(addDriverToRoster_U())
        case 4=>{dbBridge.queryPrintFormatted(rawTable("driver"))
          dbBridge.execute(updateDriverName_U())
          }
        case 5=>{dbBridge.queryPrintFormatted(rawTable("driver"))
          dbBridge.execute(updateDriverCarNumber_U())
          }
        case 6=>{dbBridge.queryPrintFormatted(rawTable("schedule"))
        dbBridge.execute(QUERIES_SQL.removeLastRaceFromSchedule_U())}
        case 0=> continue=false
      }
    }
  }

  def main(args: Array[String]) {
    dbBridge.connect()
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
    dbBridge.disconnect()
    CONSTANTS.DB_DISCONNECTED_PRINT(dbName,dbHost)
  }
}
//source:
//https://alvinalexander.com/scala/scala-jdbc-connection-mysql-sql-select-example/