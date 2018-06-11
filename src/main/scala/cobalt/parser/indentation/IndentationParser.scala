package cobalt.parser.indentation

import scala.util.parsing.combinator.RegexParsers

class IndentationParser extends RegexParsers
{
  def indentation: Parser[Int] = {
    "\n[ ]*".r ^^ { whitespace =>
      val nSpaces = whitespace.length - 1
      nSpaces
    }
  }
}
