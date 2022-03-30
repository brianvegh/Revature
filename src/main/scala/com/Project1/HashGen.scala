package com.Project1

import java.security.MessageDigest
object HashGen {
 def genHash(pass:String): String = {
  MessageDigest.getInstance("SHA-512")
    .digest(pass.getBytes("UTF-8"))
    .map("%02x".format(_)).mkString
 }
}

