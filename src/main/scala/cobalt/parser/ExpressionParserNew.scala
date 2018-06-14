package cobalt.parser

import fastparse.noApi._
import WsApi._
import cobalt.ast.{ASTNew, Ast}
import cobalt.ast.Ast.identifier

object ExpressionParserNew {

  val name: P[Ast.identifier] = LexicalParser.identifier
  val number: P[Ast.expr.Num] = P( LexicalParser.floatnumber | LexicalParser.longinteger | LexicalParser.integer | LexicalParser.imagnumber ).map(Ast.expr.Num)
  val string: P[Ast.string] = LexicalParser.stringliteral

  val expression: P[Ast.expr] = P(Chain(rExpr, BitOr))
  val rExpr: P[Ast.expr] = P(Chain(xor_expr, LtE | Lt | GtE | Gt))
  val xor_expr: P[Ast.expr] = P(Chain(bitAndExpr, BitXor))
  val bitAndExpr: P[Ast.expr] = P(Chain(shift_expr, BitAnd))
  val shift_expr: P[Ast.expr] = P(Chain(arith_expr, LShift | RShift))

  val arith_expr: P[Ast.expr] = P(Chain(term, Add | Sub))
  val term: P[Ast.expr] = P(Chain(number, Mult | Div | Mod | FloorDiv))

  def Chain(p: P[Ast.expr], op: P[Ast.operator]) = P(p ~ (op ~ p).rep).map {
    case (lhs, chunks) =>
      chunks.foldLeft(lhs) { case (lhs, (op, rhs)) =>
        Ast.expr.BinOp(lhs, op, rhs)
      }
  }

/*  expressionParser :: Parser Expr
    expressionParser
    =   newClassInstanceParser
  <|> tupleParser
    <|> parens expressionParser'
  <|> methodCallParser
    <|> ternaryParser
    <|> LongConst <$> longParser
    <|> FloatConst <$> floatParser
    <|> DoubleConst <$> doubleParser
    <|> IntConst <$> integerParser
    <|> (BoolConst True  <$ rword "True")
  <|> (BoolConst False <$ rword "False")
  <|> specialRefAsExprParser
    <|> identifierParser
    <|> stringLiteralParser*/

  // Common operators, mapped from their
  // strings to their type-safe representations
  def op[T](s: P0, rhs: T) = s.!.map(_ => rhs)

  val LShift = op("<<", Ast.operator.LShift)
  val RShift = op(">>", Ast.operator.RShift)
  val Lt = op("<", Ast.operator.Lt)
  val Gt = op(">", Ast.operator.Gt)
  val Eq = op("==", Ast.operator.Eq)
  val GtE = op(">=", Ast.operator.GtE)
  val LtE = op("<=", Ast.operator.LtE)
  val NotEq = op("<>" | "!=", Ast.operator.NotEq)
  val In = op("in", Ast.operator.In)
  val NotIn = op("not" ~ "in", Ast.operator.NotIn)
  val Is = op("is", Ast.operator.Is)
  val IsNot = op("is" ~ "not", Ast.operator.IsNot)
  val comp_op = P(LtE | GtE | Eq | Gt | Lt | NotEq | In | NotIn | IsNot | Is)
  val Add = op("+", Ast.operator.Add)
  val Sub = op("-", Ast.operator.Sub)
  val Pow = op("**", Ast.operator.Pow)
  val Mult = op("*", Ast.operator.Mult)
  val Div = op("/", Ast.operator.Div)
  val Mod = op("%", Ast.operator.Mod)
  val FloorDiv = op("//", Ast.operator.FloorDiv)
  val BitOr = op("|", Ast.operator.BitOr)
  val BitAnd = op("&", Ast.operator.BitAnd)
  val BitXor = op("^", Ast.operator.BitXor)
  val UAdd = op("+", Ast.unaryop.UAdd)
  val USub = op("-", Ast.unaryop.USub)
  val Invert = op("~", Ast.unaryop.Invert)
  val unary_op = P(UAdd | USub | Invert)
}
