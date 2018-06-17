package cobalt.parser.statement

import cobalt.ast.ASTNew.{Assign, Identifier, IntConst, Name}
import cobalt.parser.StatementParserNew
import cobalt.utils.TestUtil
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class AssignParserTest extends FunSpec with Matchers
{

  // TODO Assign parser single

  describe("Assignment parser")
  {
    it("Should parse assignment")
    {
      TestUtil.parse("let x = 10", StatementParserNew.statement) shouldBe IntConst(1)
    }

    it("Should parse mutable assignment")
    {
      TestUtil.parse("let mutable x = 10", StatementParserNew.statement) shouldBe IntConst(1)
    }

    it("Should parse with type defined")
    {
      TestUtil.parse("let x: Int = 10", StatementParserNew.statement) shouldBe IntConst(1)
    }

    // TODO "let x: Int = do"
    //      "  x"
    //      "  y"
  }

  describe("Multiple assignment parser") {
    // TODO Assign parser multiple

    // TODO "let x,y = z"

    // TODO "let mutable x,y = z"

    // TODO "let x,y: Int = z"

    // TODO "let x,y = do"
    //      "    i"
    //      "    j"

    // TODO "let x,y: Int = do"
    //      "    i"
    //      "    j"
  }
}
