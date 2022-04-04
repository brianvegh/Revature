package com
object pureAndImpure {
  //'class' variable
  var sideEffect: Int = 365

  def pure(a: Int, b: Int):Int = {
    a*(b+sideEffect)
  }
  def impure(a: Int, b: Int):Int = {
    val r=a*(b+sideEffect)
    sideEffect=sideEffect/2 ///this makes the function impure-it modifies variables outside the scope of the function
    r
  }
  //main driver
  def main(args: Array[String]): Unit = {
    //declare constants  used as input
    val int_1=34
    val int_2=56

    println(s"The value of 'sideEffect' is: $sideEffect")
    //run 'pure' first time
    val a = pure(int_1,int_2)
    println(s"Val a (1st result of fun. pure) =$a")
    
    println(s"The value of 'sideEffect' is: $sideEffect")
    //run 'impure' first time
    val b = impure(int_1,int_2)
    println(s"Val b (1st result of fun. impure) =$b")
    
    println(s"The value of 'sideEffect' is: $sideEffect")
    //run 'pure' second time
    val c = pure(int_1,int_2)
    println(s"Val a (2nd result of fun. pure) =$c")
  }//end main
}
