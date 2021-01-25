package com.rsa.cats.theroy.chapter7


object ReflectingOnFolds extends App {


  def map[A, B](l: List[A])(f: A => B): List[B] = l.foldRight(List.empty[B])((a, b) => b.::(f(a)))

  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = l.foldRight(List.empty[B])((a, b) => b.:::(f(a)))

  def filter[A](l: List[A])(f: A => Boolean): List[A] = l.foldRight(List.empty[A])((a, b) => {
    if (f(a)) {
      b.::(a)
    } else
      b
  })

  def sum[A](l: List[A])(implicit numeric: Numeric[A]): A = l.foldRight(numeric.zero)(numeric.plus)

  print(List(1, 2, 3).foldRight(List.empty[Int])((a, b) => b.::(a)))

}
