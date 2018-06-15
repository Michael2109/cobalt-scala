package cobalt.parser.expression

import cobalt.ast.ASTNew._
import cobalt.parser.ExpressionParserNew
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
      TestUtil.parse("true", ExpressionParserNew.expressionParser) shouldBe Identifier(Name("true"))
    }
    it("Should parse boolean constant `false`")
    {
      TestUtil.parse("false", ExpressionParserNew.expressionParser) shouldBe Identifier(Name("false"))
    }
  }

  describe("Relational expression parsers")
  {
    it("Should parse `less than`")
    {
      TestUtil.parse("x > 10", ExpressionParserNew.expressionParser) shouldBe RBinary(Greater,Identifier(Name("x")),IntConst(10))
    }
    it("Should parse `greater than`")
    {
      TestUtil.parse("x < 10", ExpressionParserNew.expressionParser) shouldBe RBinary(Less,Identifier(Name("x")),IntConst(10))
    }
    it("Should parse `less than equal`")
    {
      TestUtil.parse("x >= 10", ExpressionParserNew.expressionParser) shouldBe RBinary(GreaterEqual,Identifier(Name("x")),IntConst(10))
    }
    it("Should parse `greater than equal`")
    {
      TestUtil.parse("x <= 10", ExpressionParserNew.expressionParser) shouldBe RBinary(LessEqual,Identifier(Name("x")),IntConst(10))
    }
  }
}
