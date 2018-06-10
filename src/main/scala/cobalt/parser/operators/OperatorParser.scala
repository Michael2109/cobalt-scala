package cobalt.parser.operators

import cobalt.parser.MainParser

trait OperatorParser extends MainParser
{
  def operator: Parser[Any] = "+" | "-" | "*"
}
