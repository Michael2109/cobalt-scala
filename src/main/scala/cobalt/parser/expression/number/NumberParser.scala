package cobalt.parser.expression.number

import cobalt.ast.expression.Expression
import cobalt.ast.expression.number.FloatConstant

import scala.util.parsing.combinator.JavaTokenParsers

trait NumberParser extends JavaTokenParsers
{
  def floatConstant(): Parser[Expression] = floatingPointNumber ^^ ( result => new FloatConstant(result.toFloat))
}
