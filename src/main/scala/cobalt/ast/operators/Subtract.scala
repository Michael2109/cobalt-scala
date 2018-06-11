package cobalt.ast.operators

import cobalt.ast.expression.Expression

class Subtract(expression1: Expression, expression2: Expression) extends Operator(expression1, expression2)
{
  override def toString: String = "(" + expression1 + "-" + expression2 + ")"
}
