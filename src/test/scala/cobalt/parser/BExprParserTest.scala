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
class BExprParserTest extends FunSpec with Matchers
{
  describe("Boolean expression parsers")
  {
    it("Should parse boolean constant `true`")
    {
      TestUtil.parse("true", ExpressionParser.expr) shouldBe Name(identifier("true"),Load)
    }
    it("Should parse boolean constant `false`")
    {
      TestUtil.parse("false", ExpressionParser.expr) shouldBe Name(identifier("false"),Load)
    }
  }

  describe("Relational expression parsers")
  {
    // TODO Relational parser
    /*it("Should parse `less than`")
    {
      TestUtil.parse("x > 10", ExpressionParser.expr) shouldBe Name(identifier("x"),Load)
    }
    it("Should parse `greater than`")
    {
      TestUtil.parse("x < 10", ExpressionParser.expr) shouldBe BinOp(Num(1),Sub,Num(2))
    }
    it("Should parse `less than equal`")
    {
      TestUtil.parse("x >= 10", ExpressionParser.expr) shouldBe BinOp(Num(1),Mult,Num(2))
    }
    it("Should parse `greater than equal`")
    {
      TestUtil.parse("x <= 10", ExpressionParser.expr) shouldBe BinOp(Num(1),Div,Num(2))
    }*/
  }
}
