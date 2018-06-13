package cobalt.parser

import cobalt.ast.Ast.expr.Num
import cobalt.ast.Ast.identifier
import cobalt.ast.Ast.stmt.Assign
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
      // TODO "let x = y"
      TestUtil.parse("let x = 10", StatementParser.stmt) shouldBe ArrayBuffer(Assign(identifier("x"),None,true,Num(10)))
    }

    it("Should parse mutable assignment") {
      // TODO "let mutable x = y"
    }

    // TODO "let x: Int = y"

    // TODO "let x = 10"

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
