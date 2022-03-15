package com.Project0

protected object CONSTANTS {
    val DB_CONNECTED_PRINT: (String, String) => Unit = (db:String, h:String)=>println(s"#Connected to database $db on host $h.")
    val DB_DISCONNECTED_PRINT: (String, String) => Unit = (db:String, h:String)=>println(s"#Disconnected from database $db on host $h.")
    def WELCOME_MESSAGE_PRINT(): Unit = println ("Welcome to RacingConnect, an interactive database access tool for use with a SCCA Race Season mySQL database\n"+
      "----------Version.1.0---------------------------------------------------------------------------------------\n")
    val MAIN_CASE_STRING:String={
      "---- MAIN MENU --------------------------------------\n"+
      "[1]. Queries and Data Analysis\n"+
      "[2]. View/Export Raw Tables\n"+
      "[3]. Update Results, Schedule or Drivers\n"+
      "[0]. Exit Program\n"+
      "Enter your selection:"
    }
    val QUERIES_CASE_STRING:String={
      "---- QUERIES AND DATA ANALYSIS MEUN -----------------\n"+
      "[1]. View Driver Roster\n"+
      "[2]. View Race Winners\n"+
      "[3]. View Current Season Points Standings\n"+
      "[4]. View Top Three Positions for each Race\n"+
      "[5]. View Percentage Driver Finish Position stats within user-inputted range\n"+
      "[6]. View Drivers Race Finishes/DidNotFinish\n"+
      "[7]. View All Race Finishes for All Drivers\n"+
      "[8]. View All Race Finishes for Drivers matching Name Search\n"+
      "[0]. RETURN TO PREVIOUS MENU\n"+
      "Enter your selection:"
      //list of queries I have made here
    }
    val VIEWTABLES_CASE_STRING:String={
      "---- VIEW/EXPORT DATABASE TABLES-------------------\n"+
      "[1]. Drivers\n"+
      "[2]. Results\n"+
      "[3]. Schedule\n"+
      "[4]. Tracks\n"+
      "[5]. Points\n"+
      "[0]. RETURN TO PREVIOUS MENU\n"+
      "Enter your selection:"
    }
    val UPDATETABLES_CASE_STRING:String={
      "---- UPDATE RESULTS, SCHEDULE OR DRIVERS-------------\n"+
      "[1]. Add Race Result\n"+
      "[2]. Add Upcoming Race to schedule\n"+
      "[3]. Add Driver to roster\n"+
      "[4]. Update Driver Name\n"+
      "[5]. Update Driver Car Number\n"+
      "[5]. Remove Race from schedule\n"+   ///removes race from Schedule
      "[6]. Add RaceTrack to Track List\n"+
      "[0]. RETURN TO PREVIOUS MENU\n"+
      "Enter your selection:"
    }

}

