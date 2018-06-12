package cobalt.parser

import fastparse.core.Parsed

object Main
{
  def main(args: Array[String]) =
  {
    def check(str: String) = {
      val Parsed.Success(value, _) = IndentationTest.expr.parse(str)
      println(value)
    }
    check("example\n1")
    println()
  }

}
