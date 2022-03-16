package com.Project0

import scala.collection.mutable.ArrayBuffer

object importResultFromCSV extends App {
    val filepath:String=getCSV_FilePath.choosePlainFile()
    // each row is an array of strings (the columns in the csv file)
    val rows = ArrayBuffer[Array[String]]()

    // (1) read the csv data
    using(scala.io.Source.fromFile(filepath)) { source =>
      for (line <- source.getLines) {
        rows += line.split(",").map(_.trim)

      }
    }
    // (2) DO WHAT EVER WITH EACH ROW
    for (row <- rows) {
      println(s"${row(0)}|${row(1)}|${row(2)}|${row(3)}")
    }
    def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
      try {
        f(resource)
      } finally {
        resource.close()
      }
}


