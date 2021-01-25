package com.rsa.cats.theroy.chapter5

import cats.data.EitherT
import cats.instances.future._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object MonadTransform extends App {

  type Response[A] = EitherT[Future, String, A]

  val powerLevels = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  def getPowerLevel(autobot: String): Response[Int] = {
    powerLevels.get(autobot) match {
      case None => EitherT.left(Future(s"$autobot unreachable"))
      case Some(avg) => EitherT.right(Future(avg))
    }
  }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] =
    for {
      a1 <- getPowerLevel(ally1)
      a2 <- getPowerLevel(ally2)
    } yield a1 + a2 > 15


  def tacticalReport(ally1: String, ally2: String): String =
    ???

  println(canSpecialMove("w", "Hot Rod"))
  /*println(tacticalReport("Jazz", "Bumblebee"))
  // res28: String = Jazz and Bumblebee need a recharge.
  println(tacticalReport("Bumblebee", "Hot Rod"))
  // res29: String = Bumblebee and Hot Rod are ready to roll out!
  println(tacticalReport("Jazz", "Ironhide"))*/

  println(Await.result(getPowerLevel("Jazz").value, Duration.Inf))

}
