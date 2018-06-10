package cobalt.data

import cobalt.ast.expression.Identifier

class Name(nameInit: String)
{
  val name = nameInit

  override def toString: String = name
}
