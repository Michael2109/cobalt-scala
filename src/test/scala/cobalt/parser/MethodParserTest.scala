package cobalt.parser

import cobalt.ast.Ast.expr.Name
import cobalt.ast.Ast.expr_context.Load
import cobalt.ast.Ast.identifier
import cobalt.utils.TestUtil
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class MethodParserTest extends FunSpec with Matchers
{
  describe("Method parser")
  {
    it("Should parse method definitions with no fields")
    {
     // TestUtil.parse("let exampleMethod (): Int = do\n if true 1 else 2", StatementParser.stmt) shouldBe Name(identifier("true"),Load)
    }
  }
}
