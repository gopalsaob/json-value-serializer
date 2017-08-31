package com.home.serializer

import com.home.model.{MissingValue, NullValue, JsonValue}
import org.json4s.JsonAST.{JNothing, JNull}
import org.json4s.{CustomSerializer, Extraction, Formats}

class JsonValueSerializer[T](implicit mf: Manifest[T]) extends CustomSerializer[JsonValue[T]] (formats => ({
  case json =>
    implicit val fmts: Formats = formats
    json match {
      case JNothing =>
        MissingValue
      case JNull =>
        NullValue
      case x =>
        x.extract[Option[T]].map(JsonValue(_)).getOrElse(MissingValue)
    }
}, {
  case n: JsonValue[T] =>
    implicit val fmts: Formats = formats
    Extraction.decompose(n.toOption)
}))