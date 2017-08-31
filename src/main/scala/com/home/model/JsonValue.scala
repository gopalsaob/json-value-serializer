package com.home.model

object JsonValue {
  def apply[T](t: T): JsonValue[T] = SomeValue(t)
}

/**
  * An ADT representing Json Value
  */
sealed trait JsonValue[+T] {

  def toOption: Option[T] = this match {
    case SomeValue(t) => Some(t)
    case _ => None
  }

  def isNullValue: Boolean = this match {
    case NullValue => true
    case _ => false
  }

  def mapOverNull[A](a: A): Option[A] = if (isNullValue) Some(a) else None
}

case class SomeValue[+T](t: T) extends JsonValue[T]
case object MissingValue extends JsonValue[Nothing]
case object NullValue extends JsonValue[Nothing]