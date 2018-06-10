package cobalt.parser.expression

import cobalt.ast.expression.Expression
import cobalt.ast.operators.Add
import cobalt.parser.MainParser
import cobalt.parser.expression.number.NumberParser
import cobalt.parser.operators.OperatorParser


trait ExpressionParser extends MainParser with NumberParser with IdentifierParser with OperatorParser {

  def e(): Parser[Expression] = identifier() | floatConstant()

  def expression(): Parser[Expression] = {
    e() | parenthesizedExpression() | sum()
  }

  def sum(): Parser[Expression] = expression() ~ "+" ~ expression() ^^ { case (x ~ "+" ~ y) => new Add(x, y)}

  def parenthesizedExpression() = "(" ~ expression() ~ ")" ^^ {
    case ("(" ~ e ~ ")") => e
  }
}
