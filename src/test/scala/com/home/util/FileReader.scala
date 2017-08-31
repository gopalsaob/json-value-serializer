package com.home.util

import scala.io.Source

trait FileReader {

  def getFile(filePath: String): String =
    Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(filePath)).mkString

}
