package com.home

import com.home.model.{Employee, Employee2}
import com.home.serializer.JsonValueSerializer
import com.home.util.FileReader
import org.json4s.jackson.Serialization._
import org.json4s.{DefaultFormats, Formats}
import org.scalatest.{FreeSpec, Matchers}

class JsonExtractionSpec extends FreeSpec with Matchers with FileReader {

  private implicit val jsonFormats: Formats = DefaultFormats ++ Seq(new JsonValueSerializer[String])
  "JsonExtraction should work " in {
    readEmployee("employee") should be (toEmployee(readEmployee2("employee")))
    readEmployee("employeeWithMissing") should be (toEmployee(readEmployee2("employeeWithMissing")))
    readEmployee("employeeWithNull") should be (toEmployee(readEmployee2("employeeWithNull")))
  }

  private def toEmployee(employee2: Employee2) = {
    Employee(name = employee2.name.toOption, id = employee2.id.toOption)
  }

  private def readEmployee(fileName: String) = read[Employee](getFile("json/" + fileName + ".json"))
  private def readEmployee2(fileName: String) = read[Employee2](getFile("json/" + fileName + ".json"))

}
