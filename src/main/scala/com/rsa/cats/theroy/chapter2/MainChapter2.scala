package com.rsa.cats.theroy.chapter2

import cats.Functor
import cats.instances.int._
import cats.instances.list._
import cats.instances.option._
import cats.kernel.Monoid
import cats.syntax.monoid._

import scala.language.higherKinds

object MainChapter2 extends App {

  case class Order(totalCost: Double, quantity: Double)

  val monoidOrder = new Monoid[Order] {
    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }

  def add[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

  def pie[F[_], B](f: F[B])(implicit functor: Functor[F]): Unit = println(f)


  trait Printable[A] {
    self =>
    def format(value: A): String

    def contramap[B](func: B => A): Printable[B] =
      new Printable[B] {
        def format(value: B): String =
          self.format(func(value))
      }
  }

  implicit def boxPrintable[A](implicit p:Printable[A]): Printable[Box[A]] = p.contramap[Box[A]](_.value)

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }
  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if (value) "yes" else "no"
    }

  final case class Box[A](value: A)

  def format[A](a: A)(implicit printable: Printable[A]): String = printable.format(a)



  println(format(Box("hello world")))
  println(format(Box(true)))
  //println(format(Box(1,2,3)))

  pie(List(1, 2, 3))
  pie(List(1.1, 2.2))
  println(add(List(1, 2, 3)))
  println(add(List(Some(1), None, Some(3))))

}
