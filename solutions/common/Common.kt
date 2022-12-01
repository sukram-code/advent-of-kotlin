package common

import java.io.File
import kotlin.system.exitProcess

fun stopIfInvalid(args: Array<String>) {
    if (args.isEmpty()) {
        println("Required input file name as first argument")
        exitProcess(-1)
    }
}

fun inputLines(dayId: String, fileName: String): List<String> {
    return File("solutions/day$dayId/$fileName").readLines()
}
