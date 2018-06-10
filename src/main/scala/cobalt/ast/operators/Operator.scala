package cobalt.ast.operators

import cobalt.ast.AST
import cobalt.ast.expression.Expression

class Operator(expression1Init: Expression, expression2Init: Expression) extends Expression
{
  val expression1 = expression1Init
  val expression2 = expression2Init
}
