package year_2023.day01

import common.inputLines
import common.printIt
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    inputLines(year = 2023, dayId = "01", fileName = args[0])
        .toNumbers()
        .sum()
        .printIt()
}

fun List<String>.toNumbers(): List<Int> = map { string -> string.filter { it.isDigit() } }
    .map { string -> "${string.first()}${string.last()}".toInt() }
    .toList()