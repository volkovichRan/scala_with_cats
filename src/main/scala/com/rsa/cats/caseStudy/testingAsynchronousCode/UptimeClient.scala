package com.rsa.cats.caseStudy.testingAsynchronousCode


trait UptimeClient[F[_]] {

  def getUptime(hostname: String): F[Int]

}



