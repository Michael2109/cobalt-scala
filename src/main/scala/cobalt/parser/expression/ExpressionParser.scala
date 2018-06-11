package cobalt.parser.expression

import cobalt.ast.expression.Expression
import cobalt.ast.operators.{Add, Divide, Multiply, Subtract}
import cobalt.parser.MainParser
import cobalt.parser.expression.number.NumberParser
import cobalt.parser.operators.OperatorParser

// https://gist.github.com/sofoklis/3343973
trait ExpressionParser extends MainParser with NumberParser with IdentifierParser with OperatorParser {

  def e(): Parser[Expression] = identifier() | floatConstant()

  def expression(): Parser[Expression] = {
    subtract()
  }

  def sum(): Parser[Expression] = expression() ~ "+" ~ expression() ^^ { case (x ~ "+" ~ y) => new Add(x, y)}

  def parenthesizedExpression() = "(" ~ expression() ~ ")" ^^ {
    case ("(" ~ e ~ ")") => e
  }

  private def subtract(): Parser[Expression] = add() ~ rep("-" ~ add()) ^^ { case f1 ~ fs ⇒ (f1 /: fs)((f,fn) => new Subtract(f, fn._2)) }
  private def add(): Parser[Expression] = multiply() ~ rep("+" ~ multiply()) ^^ { case f1 ~ fs ⇒ (f1 /: fs)((f,fn) => new Add(f, fn._2)) }
  private def multiply(): Parser[Expression] = divide() ~ rep("*" ~ divide()) ^^ { case f1 ~ fs ⇒ (f1 /: fs)((f,fn) => new Multiply(f, fn._2)) }
  private def divide(): Parser[Expression] = (parentheses ~ rep("/" ~ parentheses())) ^^ { case f1 ~ fs ⇒ (f1 /: fs)((f,fn) => new Divide(f, fn._2)) }
  private def parentheses(): Parser[Expression] = identifier() | floatConstant() | ("(" ~ add ~ ")" ^^ { case "(" ~ exp ~ ")" ⇒ exp })

}
