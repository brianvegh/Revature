package com.Project0

import java.io.File
import javax.swing.filechooser.FileNameExtensionFilter
import scala.swing.FileChooser
import scala.util.control.Breaks.{break, breakable}

object getCSV_FilePath {
  def visualFileChooser(title:String):String ={
    var filepath:String=""
    val chooser = new FileChooser(new File("."))
    chooser.fileFilter = new FileNameExtensionFilter(".csv file","csv")
    chooser.title = title
    val result = chooser.showOpenDialog(null)
    if (result == FileChooser.Result.Approve) {
      filepath=chooser.selectedFile.getCanonicalPath
    } else {filepath=""}
    filepath
  }
  def choosePlainFile(title: String = "Select .csv File"): String = {
    //var continue = true
    var filepath: String = ""
    breakable {
      var continue = true
      var getFilepath:String=""
      while (continue) {
        getFilepath = InputValid.getString("Enter the filepath of the Result.csv you would like to add to the Result table.\n" +
          "A blank entry will open a File Chooser Dialog Box.\n" +
          "The first line of the CSV file must be column headers.\nf" +
          ".csv columns must be formatted as:\n" +
          "Race Number, Driver Name, Driver Car Number, Position, Finished[True or False],",true)
        if (getFilepath.endsWith(".csv")) {
          filepath = getFilepath
          continue = false
        } else if (getFilepath=="") {
          getFilepath = visualFileChooser(title)
          if (getFilepath.endsWith(".csv")) {
            filepath = getFilepath
            break
          } else {
            println("Invalid Filepath")
          }
        } else {
          println("Invalid Filepath")
        }
      }
    }
    filepath
  }

}
