package com.Project1

import com.Project1.InputValid._

object CraigistAnalyzer {
  val session=new SessionManager
  private var userSession:UserSession=null

  def setUserSession(us: UserSession) = {
    userSession=us
  }
  def getUserSession():UserSession =  userSession

  def main(args: Array[String]): Unit = {
    session.connect()
    CONSTANTS.WELCOME_MESSAGE_PRINT()
    var continue= true
    while (continue){
      var choice=getInt(CONSTANTS.LOG_IN_SCREEN_STRING,0,2)
      choice match {
        case 1=> {
          val s=session.login()
          if (!s.isEmpty){
            val us=s.get
            setUserSession(us)
            if (userSession.getUser.isAdmin) admin_menu()
            else main_menu()
          } else println("Login Unsuccessful")
        }
        case 2=> session.createUser()
        case 0=> continue=false
      }
    }
    session.disconnect()
  }
  def admin_menu():Unit={
    var continue= true
    while (continue){
      println()
      var choice=getInt(CONSTANTS.ADMIN_SCREEN_STRING,0,3)
      choice match {
        case 1=> main_menu()
        case 2=> session.displayAdminLogs()
        //case 3=> session.printAdminLogs()
        case 0=> {
          main_menu()
          continue = false
        }
      }
    }
  }

  def main_menu(): Unit = {
    var continue = true
    while (continue) {
      println()
      var choice = getInt(CONSTANTS.MAIN_MENU_STRING, 0, 3)
      choice match {
        case 1 => queries_menu()
        case 2 => session.displayQueryHistory(userSession)
        case 3 => session.changePassword(userSession)
        case 0 => {
          if (userSession.getUser.isAdmin) admin_menu()
          else logout()
          continue = false
        }
      }
    }
  }

  def logout() = {
    session.logout()
    userSession=null
  }

  def queries_menu():Unit={
    var continue = true
    while (continue) {
      println()
      var choice = getInt(CONSTANTS.QUERIES_MENU_STRING, 0, 6)
      choice match {
        case 1 => userSession.q_one()
        case 2 => userSession.q_two()
        case 3 => userSession.q_three()
        case 4 => userSession.q_four()
        case 5 => userSession.q_five()
        //case 6 => userSession.q_six()
        case 0 => {
          continue = false
        }
      }
    }
  }



}
