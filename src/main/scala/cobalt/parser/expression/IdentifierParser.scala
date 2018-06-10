package cobalt.parser.expression

import cobalt.ast.expression.{Expression, Identifier}
import cobalt.data.Name
import cobalt.parser.MainParser

trait IdentifierParser extends MainParser
{
  def identifier(): Parser[Expression] = """[a-z]+""".r ^^ { result => new Identifier(new Name(result)) }
}
