package cobalt.parser.statement

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class IfStatementParserTest extends FunSpec with Matchers
{
  /* TODO let codeTrue = unlines [ "if(True) then"
  , "    x"
  ]
  */

  /*
  let codeFalse = unlines [ "if(False) then"
  , "    x"
  ]
  */

  /*
      let codeElifTrue = unlines [ "if(True) then"
                               , "  i"
                               , "elif(True) then"
                               , "  j"
                               ]
   */

  /*
      let codeElifFalse = unlines [ "if(False) then"
                                , "  i"
                                , "elif(False) then"
                                , "  j"
                                ]
   */

  /*
      let codeElifElse = unlines [ "if(True) then"
                               , "  i"
                               , "elif(True) then"
                               , "  j"
                               , "else"
                               , "  k"
                               ]
   */

  /*
      let codeElse = unlines [ "if(True) then"
                           , "  i"
                           , "else"
                           , "  k"
                           ]
   */

  /*
      let codeMultipleElifsFinishedWithElse = unlines [ "if(True) then"
                                                    , "    x"
                                                    , "elif(True) then"
                                                    , "    i"
                                                    , "elif(False) then"
                                                    , "    f"
                                                    , "else"
                                                    , "    l"
                                                    ]
   */

  /*
      let codeMultipleElifsWithoutElse = unlines [ "if(True) then"
                                               , "    x"
                                               , "elif(True) then"
                                               , "    y"
                                               , "elif(False) then"
                                               , "    z"
                                               ]
   */

  /*
      let codeNestedWithoutElseNoParentheses = unlines [ "if (True) then"
                                                     , "    if (False) then "
                                                     , "        k"
                                                     , "    if True then"
                                                     , "        j"
                                                     , "    else"
                                                     , "        m"
                                                     ]
   */
}
