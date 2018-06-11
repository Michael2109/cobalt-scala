package cobalt.parser.operators


import scala.util.parsing.combinator.JavaTokenParsers

trait OperatorParser extends JavaTokenParsers
{
  def operator: Parser[Any] = "+" | "-" | "*"
}
