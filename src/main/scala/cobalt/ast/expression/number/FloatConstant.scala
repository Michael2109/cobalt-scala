package cobalt.ast.expression.number

import cobalt.ast.expression.Expression

class FloatConstant(valueInit: Float) extends Expression
{
  val value: Float = valueInit

  override def toString: String = value + "f"
}
