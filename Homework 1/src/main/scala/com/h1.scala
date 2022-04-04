//Brian Vegh
//Revature BigData Batch 3/2022
//Homework 1

package com
import scala.collection.immutable.TreeMap
import scala.collection.mutable.ArrayBuffer
import scala.util.Try
import util.control.Breaks._
import scala.io.StdIn.readLine
import scala.io.AnsiColor._

object h1 {
  def main(args: Array[String]): Unit = { //start main
//    PrintJson() //run question one
//    nameAndAge_One()//run question two
//    println(eighthCharacter("http://allaboutscala"))  //run question three
    println(priceOfDoughnuts(2.50,10))  //run question four
    println(s"The user favorite movie is: ${favMovie()}")  //run question five
    myDetails()   //run question six
    println(parseString("Vanilla Chocolate Donut 10 2.25"))//run question seven
    println(nameAndAge_Two())//run question eight
    println(filterLists())//run question nine
    println(tabulateList(11,100))//run question ten
  } //end main

  //hw question ten
  def tabulateList(q:Int, s:Int):List[Int] ={
    val l=List.tabulate(q)(n => s + n)
    l
  }
  //hw question nine
  def filterLists():List[String]={
    val a= List("Cake", "Milk", "Cheese", "Toilet Paper")
    val b= List("Bread", "Water", "Juice", "Milk", "Cheese")
    a.filter(x=>b.contains(x))
  }
  //hw question eight
  def nameAndAge_Two():TreeMap[String,Int]={
    val m=TreeMap[String, Int](("Bill",9),("Johny",8),("Tommy",11),("Cindy",13))(implicitly[Ordering[String]].reverse)
    m
  }
  //hw question seven
  def parseString(a:String):(String,Double,Int)={
    val array:Array[String]=a.split(" ")
    val stringArrayBuffer:ArrayBuffer[String]=new ArrayBuffer[String]
    var int=0
    var double=0.0
    for (x<-array){
      breakable {
        if(Try(x.toInt).isSuccess) {
          int=x.toInt
          break
        }
        if(Try(x.toDouble).isSuccess){
          double=x.toDouble
          break
        }
        stringArrayBuffer+=x
      }
    }
    val string:String=stringArrayBuffer.mkString(" ")
    (string,double,int)
  }
  //hw question six
  def myDetails():Unit={
    println("Output:\nFirst Name: Brian\nLast Name: Vegh\nFavorite Movie: Almost Famous")
  }
  //hw question 5
  def favMovie():String={
    print("What is your favorite movie of all time? :")
    readLine()
  }
  //hw quuestion 4
  def priceOfDoughnuts(price:Double, quant:Int):String={
    val product =price*quant
    f"$$$product%2.2f"
  }
  //hw question 3
  def eighthCharacter(a:String):Char={
        a.charAt(8)
  }
  //HW question two
  def nameAndAge_One():Unit={
    val name=readLine("Enter your name:")
    System.out.flush()
    val age=readLine("Enter your age:")
    System.out.flush()
    println(s"${BOLD}Name:${RESET} ${UNDERLINED}$name${RESET}")
    println(s"${BOLD}Age:${RESET} $age")
  }
  //HW question one
  def PrintJson(): Unit = {
    //create tuples
    val one = ("donut_name", "Vanilla Donut")
    val two = ("quantity_purchased", "10")
    val three = ("price", 2.5)
    val list = List(one, two, three)
    //print tuples
    println("Output:\n{")
    for (i <- list.indices) {
      val x = list(i)
      if (x._1.isInstanceOf[String]) print("\"" + x._1 + "\"") else print(x._1)
      print(":")
      if (x._2.isInstanceOf[String]) print("\"" + x._2 + "\"") else print(x._2)
      if (i != list.length - 1) println(",") else println("\n}")
    }
  } //endPrintJson
} //end object





