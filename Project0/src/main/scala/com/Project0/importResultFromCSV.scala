package com.Project0

import scala.collection.mutable.ArrayBuffer

object importResultFromCSV {

    def apply()= {
      val filepath: String = getCSV_FilePath.choosePlainFile()
      // each row is an array of strings (the columns in the csv file)
      val rows = ArrayBuffer[Array[String]]()

      // (1) read the csv data
      using(scala.io.Source.fromFile(filepath)) { source =>
        for (line <- source.getLines.drop(1)) {
          rows += line.split(",").map(_.trim)
        }
      }
      var newRowCount = rows.length
      // (2) DO WHAT EVER WITH EACH ROW
      import RaceSeasonTracker.dbBridge
      for (row <- rows) {
        // "Race Number, Driver Name, Driver Car Number, Position, Finished[True or False],"
        var newRowCount: Int = rows.length
        var raceNumber: Int = row(0).toInt
        var driverName: String = row(1)
        var carNumber: Int = row(2).toInt
        var position: Int = row(3).toInt
        var finished: Int = {
          var c = row(0).charAt(0).toLower
          if (c == 't' || c == 'y' || c == 1) 1 else 0
        }
        val s_q = s"Set @Position= $position;Set @Finished= $finished;Set @RaceNumber=$raceNumber;" +
          s"Set @DriverNameVar= '$driverName';Set @CarNumberVar=$carNumber;" +
          s"INSERT INTO Result (RaceNumber,DriverID,Position, Finished) " +
          s"Select @RaceNumber, d.id, @Position, @Finished " +
          s"From Driver d Where d.Name = @DriverNameVar and d.CarNumber = @CarNumberVar;"
        dbBridge.batchExecute(s_q.split(";").map(_+";"))
      }
      System.out.flush(); readLine("Result.csv added to Result Table. Press enter to view changes and return to previous menu.");System.out.flush();
      dbBridge.queryPrintFormatted(s"Select r.resultID_Key as 'Unique ID',r.raceNumber as 'Race Number', d.Name as 'Driver Name', r.position as 'Position', IF(r.Finished=1,'Yes','No') as 'Finished Race' " +
        s"From Result r Inner Join Driver d on r.DriverID = d.ID  Order By r.resultID_Key Desc limit $newRowCount;")
    }
    private def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
      try {
        f(resource)
      } finally {
        resource.close()
      }
}


