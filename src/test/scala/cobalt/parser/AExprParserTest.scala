package cobalt.parser

import cobalt.ast.Ast.expr.{BinOp, Name, Num}
import cobalt.ast.Ast.expr_context.Load
import cobalt.ast.Ast.identifier
import cobalt.ast.Ast.operator.{Add, Div, Mult, Sub}
import cobalt.utils.TestUtil
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class AExprParserTest extends FunSpec with Matchers
{
  describe("Number parsers")
  {
    it("Should parse integers")
    {
      TestUtil.parse("1", ExpressionParserNew.expression) shouldBe Num(1)
    }
    it("Should parse negative")
    {
      TestUtil.parse("-1", ExpressionParserNew.expression) shouldBe Num(-1)
    }
  }

  describe("Arithmetic parsers")
  {
    it("Should parse addition")
    {
      TestUtil.parse("1 + 2", ExpressionParserNew.expression) shouldBe BinOp(Num(1),Add,Num(2))
    }
    it("Should parse subtract")
    {
      TestUtil.parse("1 - 2", ExpressionParserNew.expression) shouldBe BinOp(Num(1),Sub,Num(2))
    }
    it("Should parse multiply")
    {
      TestUtil.parse("1 * 2", ExpressionParserNew.expression) shouldBe BinOp(Num(1),Mult,Num(2))
    }
    it("Should parse divide")
    {
      TestUtil.parse("1 / 2", ExpressionParserNew.expression) shouldBe BinOp(Num(1),Div,Num(2))
    }
    it("Should parse mixed")
    {
      TestUtil.parse("1 / 100 * 3 + 200 - 4", ExpressionParserNew.expression) shouldBe BinOp(BinOp(BinOp(BinOp(Num(1),Div,Num(100)),Mult,Num(3)),Add,Num(200)),Sub,Num(4))
    }
    it("Should parse parentheses")
    {
      // TODO
      // TestUtil.parse("1 / 100 * (2 + 200) - 3", ExpressionParserNew.expression) shouldBe BinOp(BinOp(BinOp(Name(identifier("x"),Load),Div,Num(100)),Mult,BinOp(Name(identifier("y"),Load),Add,Num(200))),Sub,Name(identifier("z"),Load))
    }
  }
}
