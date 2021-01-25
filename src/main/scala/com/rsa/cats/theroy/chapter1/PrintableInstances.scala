package com.rsa.cats.theroy.chapter1

final case class Cat(name: String, age: Int, color: String)

object PrintableInstances {

  implicit val printableCat = new Printable[Cat] {
    def format(a: Cat): String = s"${a.name} is a ${a.age} year-old ${a.color} cat."
  }

  implicit val printableString = new Printable[String] {
    def format(a: String): String = a
  }

  implicit val printableInt = new Printable[Int] {
    def format(a: Int): String = a.toString
  }

}
