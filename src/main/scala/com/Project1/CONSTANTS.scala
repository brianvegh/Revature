package com.Project1

object CONSTANTS {
  val DB_CONNECTED_PRINT: (String, String) => Unit = (db:String, h:String)=>println(s"#Connected to database $db on host $h.")
  val DB_DISCONNECTED_PRINT: (String, String) => Unit = (db:String, h:String)=>println(s"#Disconnected from database $db on host $h.")
  def WELCOME_MESSAGE_PRINT(): Unit = println ("\n\nWelcome to Craigslist Analyzer, an interactive data analytics\n" +
    "tool for use analyzing Craigslist Post information.\nThe tool allows users to run predefined analysis on Craigslist\n" +
    "posts gathered from every Craigslist site in the United States,\nusing Apache Spark running on Hive HDFS\n"+
    "--------------------------------------------------------V1.0--\n")

  val LOG_IN_SCREEN_STRING:String={
    "---- LOG IN SCREEN ----------------------------------\n"+
      "[1]. Log In\n"+
      "[2]. Create New User\n"+
      "[0]. Exit Program\n"+
      "Enter your selection:"
  }
  val ADMIN_SCREEN_STRING:String={
    "---- ADMIN SCREEN -----------------------------------\n"+
      "[1]. View Main Menu\n"+
      "[2]. View Logs \n"+
      "[3]. Print Logs to .csv file\n"+
      "[0]. Log Out\n"+
      "Enter your selection:"
  }
  val MAIN_MENU_STRING:String={
    "---- MAIN MENU --------------------------------------\n"+
      "[1]. Queries and Data Analysis\n"+
      "[2]. View Query History\n"+
      "[3]. Change Password\n"+
      "[0]. Log Out\n"+
      "Enter your selection:"
  }
  val QUERIES_MENU_STRING:String={
    "---- QUERIES AND DATA ANALYSIS MENU -----------------\n"+
      "[1]. Refresh Dataframes\n"+
      "[2]. Average Year of Car Made by Brand\n"+
      "[3]. Average Price for Car of X year\n"+
      "[4]. View counts of car brands for sale\n"+
      "[5]. Calculate percent of Posts that have images\n"+
      "[0]. RETURN TO PREVIOUS MENU\n"+
      "Enter your selection:"
    //list of queries I have made here
  }

}
