package cobalt.ast.operators

import cobalt.ast.expression.Expression

class Add(expression1: Expression, expression2: Expression) extends Operator(expression1, expression2)
{

  override def toString: String = "Add: " + expression1 + ", " + expression2
}
