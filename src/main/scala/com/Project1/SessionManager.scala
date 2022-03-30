package com.Project1

import com.Project1.HashGen.genHash
import com.Project1.InputValid.{getPassword, getString, getUniqueUsername, getUsername}
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks.{break, breakable}

class SessionManager() {

  val logSession=new UserSession(new User("LOGGER","LOGGER","LOGGER",false,createSparkSession=false),new SessionID(10000))
  val currentUserSessions=new ArrayBuffer[UserSession]
  var userSession:UserSession=null
  private var loggedIn:Boolean=false

  def displayQueryHistory(userSession: UserSession): Unit = {

  }
  def displayAdminLogs():Unit={
    sqlBridge.queryPrintFormatted("select * from adminuserlogs")
  }
  def printAdminLogs():Unit={
  }

  def connect() = {
    sqlBridge.connect()
  }
  def disconnect() = {
    sqlBridge.disconnect()
  }

  private val sqlBridge = new SQLbridge(
    driver = "com.mysql.cj.jdbc.Driver",
    url = s"jdbc:mysql://localhost:3306/craigslist",
    username = "root",
    password = "Password1!",
    databaseName="craigslist")

  def logEvent(logText:String,userSession: UserSession):Unit={
    val username=userSession.getUsername
    val userSessionID=userSession.getSessionID
    sqlBridge.execute(s"insert into adminuserlogs (username,usersessionID,logtext) values ('${userSession.getUsername}','${userSession.getSessionID}','$logText');")
  }

  def createUser():Unit ={
    val rs=sqlBridge.query("Select username from User;")
    var a=new ArrayBuffer[String]()
    while (rs.next()){
      a.append(rs.getString(1))
    }
    var username=getUniqueUsername("Please enter your desired username:",a.toArray)
    var firstName=getString("Enter your first name:")
    var lastName=getString("Enter your last name:")
    val passHash=genHash(getPassword("Please enter a password between 5 and 20 characters long:",5,20))
    sqlBridge.execute(s"insert into user (username,firstname,lastname,passhash) \nvalues " +
      s"('$username', '$firstName', '$lastName', '$passHash');")
    logEvent(s"User Created. New Username = $username",logSession)
  }

  def changePassword(userSession: UserSession): Unit = {
    var passwordChanged: Boolean = false
    var userEntry = ""
    while (!passwordChanged && userEntry.toLowerCase != "cancel") {
      try {
        val currentPasswordEntryHash = genHash(getPassword("Enter your current password:"))
        val newPassword1EntryHash = genHash(getPassword("Enter your new password:"))
        val newPassword2EntryHash = genHash(getPassword("ReEnter your new password"))
        val rs = sqlBridge.query(s"select un.passhash from User as un join usersession as us on un.username=us.username where" +
          s" us.username='${userSession.getUsername}' and us.usersessionid=${userSession.getSessionID};")
        rs.next()
        val currentPassHashFromSQL = rs.getString(1)
        if (currentPasswordEntryHash == currentPassHashFromSQL) {
          if (newPassword1EntryHash == newPassword2EntryHash) {
            if (currentPassHashFromSQL != newPassword1EntryHash) {
              sqlBridge.execute(s"update user set passhash = '$newPassword1EntryHash' where username='${userSession.getUsername}';")
              passwordChanged = true
              println("Your password has been updated.")
              logEvent("Password Changed",userSession)
            } else throw new Exception("Updated password cannot be the same as previous password")
          } else throw new Exception("New Passwords do not match")
        } else throw new Exception("Current Password Incorrect")
      }catch {
        case e: Exception =>
          System.out.println(e.getMessage)
          userEntry=getString("Press enter to try again, or type \"cancel\" to return to the previous menu",blankIsOK = true)
      }
    }
  }

  def login():Option[UserSession]={
    var userSession:UserSession = null
    breakable {
      var username = getUsername("Enter your username:", 5, 20)
      var rs=sqlBridge.query(s"Select username, passhash, firstname, lastname, isadmin from User u where u.username='$username'")
      if (!rs.next()){
        println("Invalid Username.")
        logEvent(s"Invalid login (bad username) by = $username",logSession)
        break
      }
      var password = getPassword("Enter your password:", 8, 20)
      val passhashed=rs.getString(2)
      if (rs.getString(2)!=genHash(password)) {
        println("Invalid Password.")
        logEvent(s"Invalid login (password) by '$username'",logSession)
        break()
      }
      val user_caseClass=new User(username,rs.getString(3),rs.getString(4),if (rs.getInt(5)==1) true else false)
      import scala.util.Random
      val confirmationCode=100+Random.nextInt(899)
      sqlBridge.execute(s"insert into usersession (username,confirmationcode) values ('$username', $confirmationCode);")
      rs=sqlBridge.query(s"Select usersessionid from usersession where username='$username' and confirmationcode=$confirmationCode ORDER BY usersessionid DESC LIMIT 1 ")
      rs.next()
      val sessionID=new SessionID(rs.getString(1).toInt)
      userSession=new UserSession(user_caseClass,sessionID)
      loggedIn=true
      println(s"User is logged in as: $username")
      logEvent("Successfull Login", userSession)
    }
    var userSession_OPTION:Option[UserSession]=None
    if (userSession!=null){
      userSession_OPTION=Some(userSession)
    }
    userSession_OPTION
  }
  def logout(): Unit={
    println(s"User ${userSession.getUsername} has been logged out.")
    logEvent("USER LOGOUT", userSession)
    userSession.closeSpark()
    userSession=null
    loggedIn=false
  }




}
