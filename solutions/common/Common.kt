package common

import java.io.File
import kotlin.system.exitProcess

fun stopIfInvalid(args: Array<String>) {
    if (args.isEmpty()) {
        println("Required input file name as first argument")
        exitProcess(-1)
    }
}

fun inputLines(year: Int = 2022, dayId: String, fileName: String): List<String> {
    return File("solutions/year_$year/day$dayId/$fileName").readLines()
}

fun Any?.printIt() = println(this)
