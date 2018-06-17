package cobalt.parser.statement

import cobalt.ast.ASTNew.IntConst
import cobalt.parser.StatementParserNew
import cobalt.utils.TestUtil
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class DoBlockParserTest extends FunSpec with Matchers
{

  // TODO Assign parser single

  describe("Assignment parser")
  {
    it("Should parse assignment")
    {
      TestUtil.parse("let x = 10", StatementParserNew.statement) shouldBe IntConst(1)
    }
  }
}
