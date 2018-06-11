package cobalt.parser.expression

import cobalt.ast.expression.{Expression, Identifier}
import cobalt.data.Name

import scala.util.parsing.combinator.JavaTokenParsers

trait IdentifierParser extends JavaTokenParsers
{
  def identifier(): Parser[Expression] = """[a-z]+""".r ^^ { result => new Identifier(new Name(result)) }
}
