package com.rsa.cats.caseStudy.testingAsynchronousCode

import cats.Id


class TestUptimeClient(hosts: Map[String, Int]) extends UptimeClient[Id] {
  def getUptime(hostname: String): Int =
    hosts.getOrElse(hostname, 0)
}


