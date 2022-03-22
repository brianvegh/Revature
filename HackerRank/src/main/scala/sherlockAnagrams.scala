package hackerRank
import java.io._
import java.math._
import java.security._
import java.text._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._
import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.collection.convert.ImplicitConversions.`map AsScala`
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching
object sherlockAnagrams {
  val hashCodes:ArrayBuffer[Int]=new ArrayBuffer[Int]()
  val strings:ArrayBuffer[String]= new ArrayBuffer[String]()

  def main(args:Array[String]):Unit={
    val s:String="abba"
    for(i<-s.indices) {
      arrayRecurse(s.slice(i, s.length - 1))
    }
//    for(i<-s.indices) {
//      arrayRecurse(s.reverse.slice(i, s.length - 1))
//   }
    println( (hashCodes.groupBy(identity).mapValues(_.size).toSeq).flatMap(t => List(t._2)))
    strings.foreach(s=> println(s))
  }
  def arrayRecurse(s:String):Unit={
    strings.addOne(s)
    if (s.length>1){
      hashCodes.addOne(s.sum.hashCode)
      arrayRecurse(s.slice(0,s.length-1))
    } else if (s.length==1) {
        hashCodes.addOne(s.sum.hashCode)
    }
  }
}

