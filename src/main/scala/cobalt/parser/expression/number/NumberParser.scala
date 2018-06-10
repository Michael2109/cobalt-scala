package cobalt.parser.expression.number

import cobalt.ast.AST
import cobalt.ast.expression.Expression
import cobalt.ast.expression.number.FloatConstant
import cobalt.parser.MainParser

trait NumberParser extends MainParser
{
  def floatConstant(): Parser[Expression] = floatingPointNumber ^^ ( result => new FloatConstant(result.toFloat))
}
