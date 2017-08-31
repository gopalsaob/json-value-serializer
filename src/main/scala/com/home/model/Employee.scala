package com.home.model

case class Employee(name: Option[String], id: Option[String])
case class Employee2(name: JsonValue[String], id: JsonValue[String])