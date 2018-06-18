package cobalt.compiler

import fastparse.noApi._
import cobalt.ast.AST._
import java.nio.file.{Path, Paths}

import cobalt.parser.{StatementParser, WsApi}

import scala.io.Source

class Compiler(commandLineOptions: Map[CommandLineOption, Any], classPath: Path, pathsToCompile: List[Path], outputDir: Path) {

  // Read in the files to be compiled

  def compile()= {

    val absolutePathsToCompile = pathsToCompile.map(path => Paths.get(classPath.toString, path.toString))

    val cobaltFiles = absolutePathsToCompile.map(x => Source.fromFile(x.toFile))

    // Parse them
    val asts = cobaltFiles.map(cobaltFile => {
      import scala.reflect.runtime.universe._
      println(Literal(Constant(cobaltFile.mkString.replace("\r", ""))))
      StatementParser.moduleParser.parse(cobaltFile.mkString.replace("\r", ""))
    })

    // "package x.y.z\nclass ClassName\n  let x(): Int = 1\n        "

    // "package x.y.z\nclass ClassName\n  let x(): Int = 1\n"
    asts.foreach(ast => ast match {
      case Parsed.Success(value, _) => println(value)
      case Parsed.Failure(a, b, c) => println("Failed compiling: " + a + " : " + b + " : " + c)
    })

    // Generate code

    // Save to destination directory
  }
}
