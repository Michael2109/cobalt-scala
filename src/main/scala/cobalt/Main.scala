package cobalt

import cobalt.parser.StatementParserNew
import fastparse.core.Parsed

object Main
{
  import sext._

  def parse(str: String) = {
    StatementParserNew.statement.parse(str) match {
      case Parsed.Success(a, b) => println(a + " : " + b)
      case Parsed.Failure(a, b, c)  => println(a + " : " + b + " : " + c)
    }
  }

  def main(args: Array[String]) =
  {
    def code =
      """if(true)
        | test
      """.stripMargin.trim.replace("\r", "")

    println("Code:")
    println(code)
    println()

    println("Tree:")
    parse(code)
    println()
  }

}
