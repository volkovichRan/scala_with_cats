package com.rsa.cats.theroy.chapter4

import cats.data.Writer
import cats.syntax.applicative._
import cats.syntax.writer._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import cats.instances.vector._

object WriterMonad extends App {

  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)


  type Pie[A] = Writer[Vector[String], A]

  def factorial2(n: Int): Pie[Int] =
    for {
      ans <- if (n == 0) {
        1.pure[Pie]
      } else {
        slowly(factorial2(n - 1).map(_ * n))
      }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans

  val Vector((logA, ansA), (logB, ansB)) = Await.result(Future.sequence(Vector(
    Future(factorial2(3).run),
    Future(factorial2(5).run)
  )), 5.seconds)

  println(logA)
  println(logB)
  println(ansA)
  println(ansB)

}
