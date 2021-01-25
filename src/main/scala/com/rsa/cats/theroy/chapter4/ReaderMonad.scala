package com.rsa.cats.theroy.chapter4

import cats.data.Reader
import cats.syntax.applicative._

object ReaderMonad extends App {

  case class Db(
                 usernames: Map[Int, String],
                 passwords: Map[String, String]
               )

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = db.usernames.get(userId).pure[DbReader]

  def checkPassword(username: String, password: String): DbReader[Boolean] = db.passwords.get(username).contains(password).pure[DbReader]

  def checkLogin(userId: Int, password: String): DbReader[Boolean] = for {
    username <- findUsername(userId)
    passwordOk <- username.map { username =>
      checkPassword(username, password)
    }.getOrElse {
      false.pure[DbReader]
    }
  } yield passwordOk


  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )
  val passwords = Map(
    "dade" -> "zerocool",
    "kate" -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)
  println(checkLogin(1, "zerocool").run(db))
  // res10: cats.Id[Boolean] = true
  println(checkLogin(4, "davinci").run(db))


}
