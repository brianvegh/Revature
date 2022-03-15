package com.Project0

protected object CONSTANTS {
    val DB_CONNECTED_PRINT: (String, String) => Unit = (db:String, h:String)=>println(s"#Connected to database $db on host $h.")
    val WELCOME_MESSAGE_PRINT: Unit = println ("Welcome to RacingConnect, an interactive database access tool for use with a SCCA Race Season mySQL database\n"+
      "----------Version.1.0---------------------------------------------------------------------------------------\n")
    val MAIN_CASE_STRING:String={
      "[1]. View/Export Tables\n"+
      "[2]. Data Analysis\n"+
      "[3]. Update Results, Schedule or Drivers\n"+
      "[0]. Exit Program\n"+
      "Enter your selection:"
    }
    val VET_CASE_STRING:String={

    }
    val DA_CASE_STRING:String={
      //list of queries I have made here
    }
    val UD_CASE_STRING:String={
      "[1]. Add Race Result\n"+
      "[2]. Add Upcoming Race to schedule\n"+
      "[3]. Add Driver to roster\n"+
      "[4]. Remove Race from schedule\n"+   ///removes race from Schedule
      "[0]. Exit Menu\n"+
      "Enter your selection:"
    }

}
