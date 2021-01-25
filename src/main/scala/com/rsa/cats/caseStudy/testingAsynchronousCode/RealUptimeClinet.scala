package com.rsa.cats.caseStudy.testingAsynchronousCode

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class RealUptimeClinet(hosts: Map[String, Int]) extends UptimeClient[Future] {
  def getUptime(hostname: String): Future[Int] =
    Future {
      hosts.getOrElse(hostname, 0)
    }
}

