package cobalt.parser

import cobalt.ast.AST
import cobalt.parser.expression.ExpressionParser

import scala.util.parsing.combinator.JavaTokenParsers

object Main extends JavaTokenParsers with ExpressionParser
{
  def parser: Parser[AST] = expression()

  def main(args: Array[String]) =
  {
      println(parseAll(parser, "(a+b)+(1+2)"))
  }
}
