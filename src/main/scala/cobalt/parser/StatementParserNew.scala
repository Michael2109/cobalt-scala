package cobalt.parser


import fastparse.noApi._
import WsApi._
import ExpressionParserNew._
import cobalt.ast.ASTNew
import cobalt.ast.ASTNew.{BlockStmt, ExprAsStmt, If, Statement}

object StatementParserNew extends Statements(0)

class Statements(indent: Int) {

  val space = P(CharIn(" \n"))
  val NEWLINE: P0 = P("\n" | End)
  val ENDMARKER: P0 = P(End)
  val indents = P("\n" ~~ " ".repX(indent))
  val spaces = P((LexicalParser.nonewlinewscomment.? ~~ "\n").repX(1))

  val ifStatementParser: P[Statement] = P(LexicalParser.kw("if") ~ "(" ~ ExpressionParserNew.expressionParser ~ ")" ~~ indentedBlock).map(x => If(x._1, x._2, null))

  // Working P(LexicalParser.kw("if") ~~ indentedBlock.map(x => If(null, x, null))
  val statement: P[Statement] = P(ifStatementParser | exprAsStmt)

  val exprAsStmt: P[Statement] = ExpressionParserNew.expressionParser.map(ExprAsStmt)

  val indentedBlock: P[Statement] = {
    val deeper: P[Int] = {
      val commentLine = P("\n" ~~ LexicalParser.nonewlinewscomment.?.map(_ => 0)).map((_, Some("")))
      val endLine = P("\n" ~~ (" " | "\t").repX(indent + 1).!.map(_.length) ~~ LexicalParser.comment.!.?)
      P(LexicalParser.nonewlinewscomment.? ~~ (endLine | commentLine).repX(1)).map {
        _.collectFirst { case (s, None) => s }
      }.filter(_.isDefined).map(_.get)
    }
    val indented: P[Statement] = P(deeper.flatMap { nextIndent =>
      new Statements(nextIndent).statement.repX(1, spaces.repX(1) ~~ (" " * nextIndent | "\t" * nextIndent)).map(x => BlockStmt(x))
    })
    indented | (" ".rep ~ statement)
  }
}
