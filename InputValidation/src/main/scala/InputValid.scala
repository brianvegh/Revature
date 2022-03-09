//  Brian Vegh
//  Revature BigData/Scala
//  March 9th, 2022
//  InputValid_CLI.scala - Utility class file with methods that insure valid user input. Contains overloaded methods with different arguments
//  defining the return values maximum length, minimum and maximum values, valid and invalid characters and more

import java.math.BigDecimal
import java.util.Scanner
import scala.util.control.Breaks.{break, breakable}

/**
 * Utility class file with methods that insure valid user input. Contains overloaded methods with different arguments
 * defining the return values maximum length, minimum and maximum values, valid and invalid characters and more
 */
class InputValid {
  private val INT_MIN = -2147483647 //default int value of lowest possible int value + 1
  private val INT_SENTINEL = INT_MIN //...for use as sentinel in comparisons
  private val NULL_STRING = null

  //private driver code getString()//private driver code getString()

  private def getString(prompt: String, illegalCharacters: String, alphaOnly: Boolean) = {
    val key = new Scanner(System.in)
    var invalid = false
    var result = ""
    do {
      System.out.println(prompt)
      result = key.nextLine
      invalid = false
      try {
        if (result.length == 0) throw new Exception("Input is blank.")
        if (illegalCharacters != null && illegalCharacters.length > 0) for (c <- result.toCharArray) {
          if (illegalCharacters.contains(String.valueOf(c))) throw new Exception("Input contains invalid characters")
        }
        if (alphaOnly) for (c <- result.toCharArray) {
          if ((c < 65 || c > 90) && (c < 97 || c > 122) && (c != ' ')) throw new Exception("Input is invalid. Only letters are valid.")
        }
      } catch {
        case e: Exception =>
          invalid = true
          System.out.println(e.getMessage)
      }
    } while ( {
      invalid
    })
    result
  }

  /**
   * returns string from user
   *
   * @param prompt prompt displayed to user
   * @return returns string from user
   */
  def getString(prompt: String): String = getString(prompt, null, false)

  /**
   * returns string that doesn't contain illegal characters
   *
   * @param prompt            prompt displayed to user
   * @param illegalCharacters characters not allowed in string
   * @return returns string from user
   */
  def getString(prompt: String, illegalCharacters: String): String = getString(prompt, illegalCharacters, false)

  /**
   * returns string that is alpha only, and doesn't contain illegal characters
   *
   * @param prompt            prompt displayed to user
   * @param illegalCharacters characters not allowd in string
   * @return returns string from user
   */
  def getStringAlphaOnly(prompt: String, illegalCharacters: String): String = getString(prompt, illegalCharacters, true)

  /**
   * returns string that is alpha only
   *
   * @param prompt prompt displayed to user
   * @return returns string from user
   */
  def getStringAlphaOnly(prompt: String): String = getString(prompt, NULL_STRING, true)

  //private driver code for getInt()//private driver code for getInt()

  private def getInt(prompt: String, illegalNumbers: String, min: Int, max: Int, throwAway: Boolean) = {
    val key = new Scanner(System.in)
    var invalid = false
    var input = ""
    var result = INT_MIN
    do {
      System.out.println(prompt)
      invalid = false
      try {
        input = key.nextLine
        if (input.length == 0) throw new Exception("Input is blank.")
        if (illegalNumbers != null && illegalNumbers.length > 0) for (c <- input.toCharArray) {
          if (illegalNumbers.contains(String.valueOf(c))) throw new Exception("Input contains invalid numbers.")
        }
          breakable {
            try {
              input.toInt
              result = input.toInt
            } catch {
              case e: NumberFormatException =>
                System.out.println("Invalid Input. Please enter a valid Integer.")
                invalid = true
              break //todo: continue is not supported

            }
            if (min != INT_MIN) {
              if (result > max) {
                invalid = true
                throw new Exception("Input must be less than or equal to " + max + ".")
              }
              if (result < min) {
                invalid = true
                throw new Exception("Input must be greater than or equal to " + min + ".")
              }
            }
          }
        }//end breakable
        catch
        {
          case e: Exception =>
            invalid = true
            System.out.println(e.getMessage)
        }

    } while ( {
      (result == INT_MIN) || invalid
    })
    result
  }

  /**
   * returns int
   *
   * @param prompt user prompt to be displayed
   * @return returns int
   */
  def getInt(prompt: String): Int = getInt(prompt, NULL_STRING, INT_SENTINEL, INT_SENTINEL, true)

  /**
   * returns int with specified min and max values
   *
   * @param prompt user prompt to be displayed
   * @param min    minimum integer accepted by user
   * @param max    maximum integer accepted by user
   * @return returns int with specified min and max values
   */
  def getInt(prompt: String, min: Int, max: Int): Int = getInt(prompt, NULL_STRING, min, max, true)

  /**
   * returns int with specified illegal characters, min and max values
   *
   * @param prompt         user prompt to be displayed
   * @param illegalNumbers numbers not allowed in input
   * @param min            minimum integer accepted by user
   * @param max            maximum integer accepted by user
   * @return
   */
  def getInt(prompt: String, illegalNumbers: String, min: Int, max: Int): Int = getInt(prompt, illegalNumbers, min, max, true)

  /**
   * returns int with specified illegal characters
   *
   * @param prompt         user prompt to be displayed
   * @param illegalNumbers numbers not allowed in input
   * @return
   */
  def getInt(prompt: String, illegalNumbers: String): Int = getInt(prompt, illegalNumbers, INT_SENTINEL, INT_SENTINEL, true)

  //private driver code for getDouble()//private driver code for getDouble()

  private def getDouble(prompt: String, illegalNumbers: Array[Double]=null, min: Double=Double.NegativeInfinity, max: Double=Double.PositiveInfinity, monetaryValue: Boolean=false) = {
    val key = new Scanner(System.in)
    var invalid = false
    var input = ""
    var result = Double.MinValue
    do {
      System.out.println(prompt)
      result = Double.MinValue
      invalid = false
      try {
        input = key.nextLine
        if (input.isEmpty) throw new Exception("Input is blank.")
        try {
          input.toDouble
          result = input.toDouble
        } catch {
          case e: NumberFormatException =>
            System.out.println("Invalid Input. Please enter a valid Double.")
            invalid = true
        }
        if (illegalNumbers != null && illegalNumbers.length > 0) for (d <- illegalNumbers) {
          if (result == d) throw new Exception("Input contains invalid numbers.")
        }
        if (min != Double.NegativeInfinity) if (result < min) throw new Exception("Invalid input. Input is outside allowed range.")
        if (max != Double.PositiveInfinity) if (result > max) throw new Exception("Invalid input. Input is outside allowed range.")
        if (monetaryValue) if (BigDecimal.valueOf(result).scale > 2) throw new Exception("Invalid Monetary input. Only two decimal places allowed.")
      } catch {
        case e: Exception =>
          invalid = true
          System.out.println(e.getMessage)
      }
    } while ( {
      (result == Double.MinValue) && invalid
    })
    result
  }

  /**
   * returns a valid double
   *
   * @param prompt
   * @return
   */
  def getDouble(prompt: String): Double = getDouble(prompt)

  /**
   * returns valid double, with illegal values defined
   *
   * @param prompt prompt for user input displayed
   * @param illegalNumbers
   * @return
   */
  def getDouble(prompt: String, illegalNumbers: Array[Double]): Double = getDouble(prompt, illegalNumbers)

  /**
   * returns valid double, with minimum and maximum values defined
   *
   * @param prompt
   * @param min
   * @param max
   * @return
   */
  def getDouble(prompt: String, min: Double, max: Double): Double = getDouble(prompt, null, min, max)

  /**
   * returns valid double, with illegal numbers, minimum and maximum values defined
   *
   * @param prompt
   * @param illegalNumbers
   * @param min
   * @param max
   * @return
   */
  def getDouble(prompt: String, illegalNumbers: Array[Double], min: Double, max: Double): Double = getDouble(prompt, illegalNumbers, min, max)

  /**
   * returns valid double in the format of dollar amount
   *
   * @param prompt
   * @param illegalNumbers
   * @return
   */
  def getDollarAmount(prompt: String, illegalNumbers: Array[Double]): Double = getDouble(prompt, illegalNumbers=null, Double.NegativeInfinity,Double.PositiveInfinity, true)

  //private driver code for getChar()//private driver code for getChar()

  private def getChar(prompt: String, validChars: String=null, invalidChars: String=null, aplhaOnly: Boolean) = {
    val key = new Scanner(System.in)
    var invalid = false
    var input = ""
    var result = ' '
    do {
      System.out.println(prompt)
      invalid = false
      try {
        var throwException = true
        input = key.nextLine
        if (input.isEmpty) throw new Exception("Input is blank.")
        input = String.valueOf(input.charAt(0))
        if (validChars != null && validChars.nonEmpty) {
          if (validChars.contains(input)) throwException = false
          if (throwException) throw new Exception("Input is invalid.")
        }
        if (invalidChars != null && invalidChars.nonEmpty) for (c <- invalidChars.toCharArray) {
          if (input.contains(String.valueOf(c))) throwException = false
          if (throwException) throw new Exception("Input is invalid.")
        }
        result = input.charAt(0)
        if (aplhaOnly) if ((result < 65 || result > 90) && (result < 97 || result > 122)) throw new Exception("Input is invalid. Only letters are valid.")
      } catch {
        case e: Exception =>
          invalid = true
          System.out.println(e.getMessage)
      }
    } while ( {
      invalid
    })
    result
  }

  /**
   * returns the first char in user input as char
   *
   * @param prompt prompt displayed to the user
   * @return returns the first char in user input
   */
  def getChar(prompt: String): Char = getChar(prompt,null,null,false)

  /**
   * returns the first char in user input as char, with options to include or exclude chars
   *
   * @param prompt prompt displayed to the user
   * @param validChars
   * @param invalidChars
   * @return returns the first char in user input
   */
  def getChar(prompt: String, validChars: String, invalidChars: String): Char = getChar(prompt, validChars, invalidChars,false)

  /**
   * returns a letter (no symbols or numbers) as a char to the user
   *
   * @param prompt prompt displayed to the user
   * @return returns the first char in user input
   */
  def getLetter(prompt: String): Char = getChar(prompt, null,null, true)

  /**
   * returns a boolean from user input, accepting char T,t,F,f to make determination
   *
   * @param prompt
   * @return
   */
  def getTrueOrFalse(prompt: String): Boolean = {
    val c = getChar(prompt, "TtFf", "", true)
    c == 116 || c == 84
  }

  /**
   * returns a boolean from user input, accepting char Y,y,N,n to make determination
   *
   * @param prompt
   * @return
   */
  def getYesOrNo(prompt: String): Boolean = {
    val c = getChar(prompt, "YyNn", "", true)
    c == 121 || c == 89
  }

  /**
   * returns a boolean from user input, accepting char Y,y,N,n and T,t,F,f to make determination
   *
   * @param prompt
   * @return
   */
  def getBoolean(prompt: String): Boolean = {
    val c = getChar(prompt, "YyNnTtFf", "", true)
    c == 121 || c == 116 || c == 89 || c == 84
  }
}//end class
