import cobalt.ast.Ast.expr.Num
import cobalt.parser.ExpressionParser
import fastparse.core.Parsed
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, FunSpec, Matchers, TestSuite}
import fastparse.noApi._

@RunWith(classOf[JUnitRunner])
class ArithmeticTest extends FlatSpec with Matchers
{

  def parse(text: String, parser: P[_]) =
  {
    val Parsed.Success(value, _) = parser.parse(text)
    value
  }

  "Numbers" should "parse" in
  {
    val ast = parse("1", ExpressionParser.number)
    ast shouldBe Num(1)
  }

  "Arithmetic Expressions" should "parse addition" in
  {

  }


}
