package cobalt.parser

import cobalt.ast.AST
import cobalt.parser.module.ModuleParser

import scala.util.parsing.combinator.JavaTokenParsers

object Main extends JavaTokenParsers with ModuleParser
{
  def parser: Parser[AST] = ifStatement

  def main(args: Array[String]) =
  {
    val code =
      """
        |if((a+b)+(1+2) - 5 * 7 / 3)
        |    1 + 2
      """.stripMargin

    println(parseAll(indentBlock, code))
  }
}
