package com.Project1
import com.lambdaworks.jacks._

import java.sql.ResultSet
import scala.collection.mutable.{ArrayBuffer, Map}

object MakeJSON {
  def apply(rs:ResultSet):String= {
    val meta = rs.getMetaData

    // Get total column count from metadata
    val colsCount = meta.getColumnCount
    var colsMap: Map[Int, String] = Map.empty[Int, String]

    // Create ArrayBuffer[ArrayBuffer[Map(String, String)]]
    var final_array = ArrayBuffer[ArrayBuffer[scala.collection.mutable.Map[String, String]]]()

    // Fill colsMap from columnName
    for (i <- 1 to colsCount) {
      colsMap += i -> meta.getColumnName(i)
    }

    while (rs.next()) {
      var result_array = ArrayBuffer[scala.collection.mutable.Map[String, String]]()
      for (i <- 1 to colsCount) {
        var temp_map = scala.collection.mutable.Map[String, String]()
        val cols = colsMap(i)
        val value = rs.getObject(i)
        temp_map(s"$cols") = s"$value"
        result_array += temp_map
      }
      final_array += result_array
    }

    // for Array: use JacksMapper to convert ArrayBuffer[Map(String, String)] to [{"column1":"value1"},{"column2":"value2"}]

    val json_string:String = JacksMapper.writeValueAsString(final_array)
    json_string
  }
}
