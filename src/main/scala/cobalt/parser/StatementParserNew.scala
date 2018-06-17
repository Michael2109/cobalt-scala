package cobalt.parser


import fastparse.noApi._
import WsApi._
import cobalt.ast.ASTNew._

object StatementParserNew extends Statements(0)

class Statements(indent: Int) {

  val space = P(CharIn(" \n"))
  val NEWLINE: P0 = P("\n" | End)
  val ENDMARKER: P0 = P(End)
  val indents = P("\n" ~~ " ".repX(indent))
  val spaces = P((LexicalParser.nonewlinewscomment.? ~~ "\n").repX(1))

  val assignParser: P[Assign] = P(LexicalParser.kw("let") ~ ("mutable").!.? ~ ExpressionParserNew.nameParser ~ (":" ~ ExpressionParserNew.typeRefParser).? ~ "=" ~ blockParser).map(x => Assign(x._2, x._3, x._1.isEmpty, x._4))

  val blockParser: P[Block] = P(doBlock | ExpressionParserNew.expressionParser.map(Inline))

  val doBlock: P[Block] = P(LexicalParser.kw("do") ~~ indentedBlock).map(DoBlock)

  val ifStatementParser: P[Statement] = P(LexicalParser.kw("if") ~ "(" ~ ExpressionParserNew.expressionParser ~ ")" ~~ indentedBlock).map(x => If(x._1, x._2, null))

  // val reassignParser: P[Reassign] = P

  val statement: P[Statement] = P(assignParser | ifStatementParser | exprAsStmt)

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
