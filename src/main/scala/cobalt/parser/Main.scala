package cobalt.parser

import fastparse.core.Parsed

object Main
{
  import sext._

  def parse(str: String) = {
    val Parsed.Success(value, _) = StatementParser.stmt.parse(str)
    println(value.treeString)
  }

  def main(args: Array[String]) =
  {
    def code =
      """let functionname( parameters )= do
        |  if true then
        |    10
        |  elif false then
        |    15
        |  else
        |    20
        |
      """.stripMargin.trim.replace("\r", "")

    println("Code:")
    println(code)
    println()

    println("Tree:")
    parse(code)
    println()
  }

}
