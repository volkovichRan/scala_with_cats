package com.rsa.cats.caseStudy.mapReduce

import cats.Monoid
import cats.instances.int._
import cats.instances.string._
import cats.syntax.semigroup._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

object MainMapReduce extends App {

  def foldMap[A, B: Monoid](v: Vector[A])(f: A => B): B = v.foldLeft(Monoid[B].empty)((a, b) => Monoid.combine(a, f(b)))

  def foldMap2[A, B](v: Vector[A])(f: A => B)(implicit i: Monoid[B]): B = v.map(f(_)).foldLeft(i.empty)(_ |+| _)

  def parallelFoldMap[A, B: Monoid](values: Vector[A])(func: A => B): Future[B] = {
    val numCores = Runtime.getRuntime.availableProcessors
    val groupSize = (values.size / numCores).ceil.toInt

    val groups: Iterator[Vector[A]] = values.grouped(groupSize)

    val futures: Iterator[Future[B]] =
      groups map { group =>
        Future {
          group.foldLeft(Monoid[B].empty)(_ |+| func(_))
        }
      }

    Future.sequence(futures) map { iterable =>
      iterable.foldLeft(Monoid[B].empty)(_ |+| _)
    }

  }

  /*def parallelFoldMap2[A, B: Monoid]
  (values: Vector[A])
  (func: A => B): Future[B] = {
    val numCores = Runtime.getRuntime.availableProcessors
    val groupSize = (1.0 * values.size / numCores).ceil.toInt
    values.grouped(groupSize).toVector.traverse(group => Future(group.toVector.foldMap(func))).map(_.combineAll)
  }*/

  println(foldMap(Vector(1, 2, 3))(identity))
  println(foldMap2(Vector(1, 2, 3))(_.toString + "! "))
  val result: Future[Int] =
    parallelFoldMap((1 to 1000000).toVector)(identity)
  Await.result(result, 1.second)
  println(result.value.get.get)

}
