package com.rsa.cats.caseStudy.dataValidation

object DataValidationMain extends App {

  trait Check[E, A] {
    def apply(value: A): Either[E, A]
    def and(that: Check[E, A]): Check[E, A] = ???
    // other methods...
  }

}
