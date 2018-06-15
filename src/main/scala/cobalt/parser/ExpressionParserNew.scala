package cobalt.parser

import fastparse.noApi._
import WsApi._
import cobalt.ast.ASTNew._
import cobalt.ast.{ASTNew}

object ExpressionParserNew {

  val name: P[ASTNew.Identifier] = LexicalParser.identifier.map(x => Identifier(Name(x)))
  val number: P[IntConst] = P( LexicalParser.floatnumber | LexicalParser.longinteger | LexicalParser.integer | LexicalParser.imagnumber ).map(x => IntConst(x.toInt))
  val stringLiteral: P[StringLiteral] = LexicalParser.stringliteral.map(x => StringLiteral(x))

  val expression: P[Expression] = P(Chain(rExpr, and | or))
  val rExpr: P[Expression] = P(Chain(arith_expr, LtE | Lt | GtE | Gt))
  //val xor_expr: P[Expr] = P(Chain(bitAndExpr, BitXor))
  //val bitAndExpr: P[Expr] = P(Chain(shift_expr, BitAnd))
  //val shift_expr: P[Expr] = P(Chain(arith_expr, LShift | RShift))

  val arith_expr: P[Expression] = P(Chain(term, add | subtract))
  val term: P[Expression] = P(Chain(allExpressions, multiply | divide /*| Mod | FloorDiv*/))

  val parens: P[Expression] = P( "(" ~ (expression) ~ ")" )

  val allExpressions = number | name | stringLiteral | parens
/*

  private def subtract(): Parser[Expr] = add() ~ ("-" ~ add()).rep.map(x => x)
  private def add(): Parser[Expr] = multiply() ~ rep("+" ~ multiply()) ^^ { case f1 ~ fs ⇒ (f1 /: fs)((f,fn) => new Add(f, fn._2)) }
  private def multiply(): Parser[Expr] = divide() ~ rep("*" ~ divide()) ^^ { case f1 ~ fs ⇒ (f1 /: fs)((f,fn) => new Multiply(f, fn._2)) }
  private def divide(): Parser[Expr] = (parentheses ~ rep("/" ~ parentheses())) ^^ { case f1 ~ fs ⇒ (f1 /: fs)((f,fn) => new Divide(f, fn._2)) }
  private def parentheses(): Parser[Expr] = identifier() | floatConstant() | ("(" ~ add ~ ")" ^^ { case "(" ~ exp ~ ")" ⇒ exp })
*/


  def Chain(p: P[Expression], op: P[ASTNew.Operator]) = P(p ~ (op ~ p).rep).map {
    case (lhs, chunks) =>
      chunks.foldLeft(lhs) { case (lhs, (operator, rhs)) =>
        operator match {
          case op: ABinOp => new ABinary(op, lhs, rhs)
          case op: BBinOp => new BBinary(op, lhs, rhs)
          case op: RBinOp => new RBinary(op, lhs, rhs)
        }

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

//  val LShift = op("<<", Ast.operator.LShift)
//  val RShift = op(">>", Ast.operator.RShift)
  val Lt = op("<", ASTNew.Less)
  val Gt = op(">", ASTNew.Greater.asInstanceOf[Operator])
  val Eq = op("==", ASTNew.Equal.asInstanceOf[Operator])
  val GtE = op(">=", ASTNew.GreaterEqual.asInstanceOf[Operator])
  val LtE = op("<=", ASTNew.LessEqual.asInstanceOf[Operator])
//  val NotEq = op("<>" | "!=", Ast.operator.NotEq)
//  val In = op("in", Ast.operator.In)
//  val NotIn = op("not" ~ "in", Ast.operator.NotIn)
//  val Is = op("is", Ast.operator.Is)
//  val IsNot = op("is" ~ "not", Ast.operator.IsNot)
  val comp_op = P(LtE | GtE | Eq | Gt | Lt /*| NotEq | In | NotIn | IsNot | Is*/)
  val add = op("+", ASTNew.Add.asInstanceOf[Operator])
  val subtract = op("-", ASTNew.Subtract.asInstanceOf[Operator])
//  val Pow = op("**", Ast.operator.Pow)
  val multiply = op("*", ASTNew.Multiply.asInstanceOf[Operator])
  val divide = op("/", ASTNew.Divide.asInstanceOf[Operator])
//  val Mod = op("%", Ast.operator.Mod)
//  val FloorDiv = op("//", Ast.operator.FloorDiv)
//  val BitOr = op("|", Ast.operator.BitOr)
//  val BitAnd = op("&", Ast.operator.BitAnd)
//  val BitXor = op("^", Ast.operator.BitXor)
//  val UAdd = op("+", Ast.unaryop.UAdd)
//  val USub = op("-", Ast.unaryop.USub)
//  val Invert = op("~", Ast.unaryop.Invert)
  // val unary_op = P(UAdd | USub | Invert)

  val and = op("&&", ASTNew.And.asInstanceOf[Operator])
  val or = op("||", ASTNew.Or.asInstanceOf[Operator])
}
