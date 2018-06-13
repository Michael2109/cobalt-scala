package cobalt.utils

import fastparse.core.Parsed
import fastparse.noApi.P

object TestUtil {

  def parse(text: String, parser: P[_]) =
  {
    val Parsed.Success(value, _) = parser.parse(text)
    value
  }

}
