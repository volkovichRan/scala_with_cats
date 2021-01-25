/*
package com.rsa.cats.Theroy.chapter6

import cats.data.Validated
import cats.syntax.either._


object Validation extends App {

  case class User(name: String, age: Int)

  type FormData = Map[String, String]
  type FailFast[A] = Either[List[String], A]
  type FailSlow[A] = Validated[List[String], A]
  type NumFmtExn = NumberFormatException

  def getValue(name: String)(data: FormData): FailFast[String] = data.get(name).toRight(List(s"$name is not valid key"))

  def readName(data: FormData): FailFast[String] =
    getValue("name")(data).
      flatMap(nonBlank("name"))

  def readAge(data: FormData): FailFast[Int] =
    getValue("age")(data).
      flatMap(nonBlank("age")).
      flatMap(parseInt("age")).
      flatMap(nonNegative("age"))


  def parseInt(name: String)(data: String): FailFast[Int] =
    Either.catchOnly[NumFmtExn](data.toInt).
      leftMap(_ => List(s"$name must be an integer"))

  def nonBlank(name: String)(data: String): FailFast[String] = Right(data).ensure(List(s"$name cannot be blank"))(_.nonEmpty)

  def nonNegative(name: String)(data: Int): FailFast[Int] = Right(data).ensure(List(s"$name must be non-negative"))(_ >= 0)

  def readUser(data: FormData): FailSlow[User] =
    (
      readName(data).toValidated,
      readAge(data).toValidated
    ).mapN(User.apply)


}
*/
