package cobalt.parser

import fastparse.WhitespaceApi
import fastparse.all._

object IndentationTest {

/*
  val White = WhitespaceApi.Wrapper{
    import fastparse.all._
    NoTrace(" ".rep)
  }
  import fastparse.noApi._

*/

  class Parser(indent: Int) {

    //import White._

    val word: P[String] = P("example".!)

    val newline: P[String] = P("\n".!)

    val number: P[String] = P(CharIn('0' to '9').rep(1).!)

    val blockBody: P[Seq[String]] = P("\n").flatMap(i => new Parser(0).number.rep())

    val deeper: P[Int] = P(" ".rep(indent + 0).!.map(_.length))

    val section: P[(String, Seq[String])] = P(word ~ blockBody ~ End)
  }

  val expr = new Parser(indent = 0).section
}
