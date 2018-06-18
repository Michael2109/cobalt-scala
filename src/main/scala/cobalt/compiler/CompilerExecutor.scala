package cobalt.compiler

import java.nio.file.{Path, Paths}

object CompilerExecutor {

  val usage = "Usage: -cp <class_path> -d <destination_dir>"

  def main(args: Array[String]): Unit = {
    if (args.length == 0) println(usage)
    val arglist = args.toList
    type OptionMap = Map[CommandLineOption, String]

    def nextOption(commandLineOptions: OptionMap, list: List[String]): OptionMap = {
      def isSwitch(s: String) = (s(0) == '-')

      list match {
        case Nil => commandLineOptions
        case "-cp" :: value :: tail =>
          nextOption(commandLineOptions ++ Map(ClassPath -> value), tail)
        case "-d" :: value :: tail =>
          nextOption(commandLineOptions ++ Map(DestinationPath -> value), tail)
        case value :: tail => nextOption(commandLineOptions ++ Map(FilePath -> value), tail)
      }
    }

    val options = nextOption(Map(), arglist)
    println(options)

    new Compiler(options, Paths.get(options.get(ClassPath).get), List[Path](Paths.get(options.get(FilePath).get)), Paths.get(options.get(DestinationPath).get))
  }
}

trait CommandLineOption
case object ClassPath extends CommandLineOption
case object DestinationPath extends CommandLineOption
case object FilePath extends CommandLineOption
