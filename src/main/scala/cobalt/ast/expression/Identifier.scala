package cobalt.ast.expression

import cobalt.data.Name

class Identifier(nameInit: Name) extends Expression
{
  val name = nameInit

  override def toString: String = "Identifier: " + name
}
