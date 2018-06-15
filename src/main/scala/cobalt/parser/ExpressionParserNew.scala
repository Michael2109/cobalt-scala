package cobalt.parser

import fastparse.noApi._
import WsApi._
import cobalt.ast.ASTNew._
import cobalt.ast.{ASTNew}

object ExpressionParserNew {

  val annotationParser: P[Annotation] = nameParser.map(Annotation)

  val identifierParser: P[ASTNew.Identifier] = LexicalParser.identifier.map(x => Identifier(Name(x)))
  val nameParser: P[Name] = LexicalParser.identifier.map(x => Name(x))
  val numberParser: P[IntConst] = P( LexicalParser.floatnumber | LexicalParser.longinteger | LexicalParser.integer | LexicalParser.imagnumber ).map(x => IntConst(x.toInt))
  val stringLiteral: P[StringLiteral] = LexicalParser.stringliteral.map(x => StringLiteral(x))

  val expressionParser: P[Expression] = P(Chain(rExprParser, and | or))
  private val rExprParser: P[Expression] = P(Chain(arith_exprParser, LtE | Lt | GtE | Gt))
  private val arith_exprParser: P[Expression] = P(Chain(termParser, add | subtract))
  private val termParser: P[Expression] = P(Chain(allExpressionsParser, multiply | divide ))
  private val parensParser: P[Expression] = P( "(" ~ (expressionParser) ~ ")" )

  private val allExpressionsParser = numberParser | identifierParser | stringLiteral | parensParser

  def finalModifierParser: P[ASTNew.Final.type] = P("final").map(x => Final)

  def methodCallParser: P[MethodCall] = (nameParser ~ "(" ~ expressionParser.rep(sep = ",") ~ ")").map(x => MethodCall(x._1, BlockExpr(x._2)))

  def newClassInstanceParser: P[NewClassInstance] = ("new" ~ typeRefParser ~ "(" ~ expressionParser.rep(sep = ",") ~ ")").map(x => NewClassInstance(x._1, BlockExpr(x._2), None))

  def typeRefParser: P[Type] = nameParser.map(x => TypeRef(RefLocal(x)))

  private def Chain(p: P[Expression], op: P[ASTNew.Operator]) = P(p ~ (op ~ p).rep).map {
    case (lhs, chunks) =>
      chunks.foldLeft(lhs) { case (lhs, (operator, rhs)) =>
        operator match {
          case op: ABinOp => new ABinary(op, lhs, rhs)
          case op: BBinOp => new BBinary(op, lhs, rhs)
          case op: RBinOp => new RBinary(op, lhs, rhs)
        }

      }
  }

  def op[T](s: P0, rhs: T) = s.!.map(_ => rhs)
  val Lt = op("<", ASTNew.Less)
  val Gt = op(">", ASTNew.Greater.asInstanceOf[Operator])
  val Eq = op("==", ASTNew.Equal.asInstanceOf[Operator])
  val GtE = op(">=", ASTNew.GreaterEqual.asInstanceOf[Operator])
  val LtE = op("<=", ASTNew.LessEqual.asInstanceOf[Operator])
  val comp_op = P(LtE | GtE | Eq | Gt | Lt)
  val add = op("+", ASTNew.Add.asInstanceOf[Operator])
  val subtract = op("-", ASTNew.Subtract.asInstanceOf[Operator])
  val multiply = op("*", ASTNew.Multiply.asInstanceOf[Operator])
  val divide = op("/", ASTNew.Divide.asInstanceOf[Operator])
  val and = op("&&", ASTNew.And.asInstanceOf[Operator])
  val or = op("||", ASTNew.Or.asInstanceOf[Operator])
}
