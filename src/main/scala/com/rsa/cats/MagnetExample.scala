package com.rsa.cats

object MagnetExample extends App {

 trait Pie {
    type Result
    def test(): Result
  }


  implicit def intMagnetExample(q: Int) = new Pie {
    override type Result = Int
    override def test(): Int = q * q
  }

  implicit def stringMagnetExample(s: String) = new Pie {
    override type Result = String

    override def test(): String = s + s
  }

  def test(m: Pie) = m.test()

  test("lol")
  test(5)
  print("lol")

}

