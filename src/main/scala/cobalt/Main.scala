package cobalt

import java.io.{BufferedOutputStream, FileOutputStream}

import cobalt.parser.{ExpressionParser, StatementParser, WsApi}
import fastparse.core.Parsed
import fastparse.noApi._
import cobalt.ast.AST
import fastparse.noApi._
import WsApi._
import cobalt.ast.AST.{Module, Name}
import cobalt.code_gen.CodeGen

object Main
{
  import sext._

  def main(args: Array[String]): Unit =
  {


    val moduleCode =
      """package x.y.z
        |class ClassName
        |
        |  let x(): Int = do
        |    let x = 1
        |    let y = 2
      """.stripMargin.replace("\r", "")
    val bos = new BufferedOutputStream(new FileOutputStream("Test.class"))

    StatementParser.moduleParser.parse(moduleCode) match
    {
      case Parsed.Success(a, b) => {
        bos.write(CodeGen.genCode(AST.moduleToModuleIR(a)))
        println(a.treeString + " : " + b)
      }
      case Parsed.Failure(a, b, c)  => println(a + " : " + b + " : " + c)
    }

    bos.close() // You may end up with 0 bytes file if not calling close.

  }

}
