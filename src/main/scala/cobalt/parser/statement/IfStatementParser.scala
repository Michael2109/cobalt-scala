package cobalt.parser.statement

import cobalt.ast.statement.IfStatement
import cobalt.parser.expression.ExpressionParser

class IfStatementParser extends StatementParser
{/*
  def ifStatement(): Parser[IfStatement] =  """if\(""".r ~ new ExpressionParser()expression() ~ """\)""".r ^^ { result => new IfStatement(result._1._2, result._1._2) }*/
}
