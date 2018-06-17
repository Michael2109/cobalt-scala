package cobalt

import cobalt.parser.{ExpressionParserNew, StatementParserNew, WsApi}
import fastparse.core.Parsed
import fastparse.noApi._
import cobalt.ast.ASTNew
import fastparse.noApi._
import WsApi._
import cobalt.ast.ASTNew.Name

object Main
{
/*
  import sext._

  def parse(str: String) = {
    StatementParserNew.statement.parse(str) match {
      case Parsed.Success(a, b) => println(a + " : " + b)
      case Parsed.Failure(a, b, c)  => println(a + " : " + b + " : " + c)
    }
  }
*/

  def main(args: Array[String]): Unit =
  {/*
    def code =
      """let x = 10
      """.stripMargin.trim.replace("\r", "")

    println("Code:")
    println(code)
    println()

    println("Tree:")
    parse(code)
    println()*/

    def ripplingNipsParser: P[Seq[Name]] = P(ExpressionParserNew.nameParser.rep()).map(x => x)

    ripplingNipsParser.parse("rippling nips test some other text") match {
      case Parsed.Success(a, b) => println(a + " : " + b)
    }

    println()

  }

}
