package cobalt

import cobalt.parser.{ExpressionParser, StatementParser, WsApi}
import fastparse.core.Parsed
import fastparse.noApi._
import cobalt.ast.AST
import fastparse.noApi._
import WsApi._
import cobalt.ast.AST.Name

object Main
{

  import sext._

  def parse(str: String) = {
    StatementParser.statement.parse(str) match {
      case Parsed.Success(a, b) => println(a + " : " + b)
      case Parsed.Failure(a, b, c)  => println(a + " : " + b + " : " + c)
    }
  }


  def main(args: Array[String]): Unit =
  {
    def code =
      """let x = 10
      """.stripMargin.trim.replace("\r", "")

    println("Code:")
    println(code)
    println()

    println("Tree:")
    parse(code)
    println()

  }

}
