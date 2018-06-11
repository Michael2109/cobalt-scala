package cobalt.parser.statement

import cobalt.ast.statement.IfStatement
import cobalt.parser.expression.ExpressionParser

trait IfStatementParser extends StatementParser with ExpressionParser
{
  def ifStatement(): Parser[IfStatement] =  """if\(""".r ~ expression() ~ """\)""".r ^^ { result => new IfStatement(result._1._2, result._1._2) }
}
