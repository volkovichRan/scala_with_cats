package com.rsa.cats.caseStudy.crdts

object CrdtsMain extends App {

  final case class GCounter(counters: Map[String, Int]) {
    def increment(machine: String, amount: Int) = {
      val value = amount + counters.getOrElse(machine, 0)
      GCounter(counters + (machine -> value))
    }

    def merge(that: GCounter): GCounter =
      GCounter(that.counters ++ this.counters.map {
        case (k, v) =>
          k -> (v max that.counters.getOrElse(k, 0))
      })

    def total: Int = counters.values.sum
  }

  val s = Set(1,2)
  val q = Set(3,2)

  println(s union q)

}
