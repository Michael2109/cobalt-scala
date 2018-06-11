package cobalt.ast.statement

import cobalt.ast.expression.Expression

class IfStatement(conditionInit: Expression, ifBodyInit: Expression) extends Statement
{
  val condition = conditionInit

  val ifBody = ifBodyInit

  override def toString: String = "if(" + condition + "){" + ifBody + "}"
}
