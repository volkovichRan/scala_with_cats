package com.rsa.cats.theroy.chapter1

import cats.instances.int._
import cats.instances.option._
import cats.instances.string._
import cats.syntax.eq._
import cats.syntax.show._
import cats.{Eq, Show}
//import com.rsa.cats.PrintableInstances._
//import com.rsa.cats.PrintableSyntax._

object MainChapter1 extends App {

  implicit val showCat = Show.show[Cat](a => s"${a.name} is a ${a.age} year-old ${a.color} cat.")
  implicit val eqCat : Eq[Cat]= Eq.instance[Cat]{
    (cat1, cat2) => cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color
  }

  val cat = Cat("pie", 4, "red")
  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")
  val optionCat1 = Option(cat1)
  val optionCat2 = Some(cat1)

 println(optionCat1 === optionCat2)



  println(cat1 === cat2)
  //cat.print
  //"lol".print
  print(cat.show)

  println(123 === 1234)

  val showInt = Show.apply[Int]
  4.show
}

