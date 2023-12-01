package year_2023.day01

import common.inputLines
import common.printIt
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    val lines = inputLines(year = 2023, dayId = "01", fileName = args[0])
    solvePartOne(lines)
    solvePartTwo(lines)
}

fun solvePartOne(lines: List<String>) {
    "Part 1:".printIt()
    lines
        .extractNumbers()
        .sum()
        .printIt()
}

fun solvePartTwo(lines: List<String>) {
    "Part 2:".printIt()
    lines
        .wordsToDigits()
        .extractNumbers()
        .sum()
        .printIt()
}

fun List<String>.extractNumbers(): List<Int> = map { string -> string.filter { it.isDigit() } }
    .map { string -> if (string.isEmpty()) 0 else "${string.first()}${string.last()}".toInt() }

fun List<String>.wordsToDigits(): List<String> = map { string -> string.replaceWordsToWordsWithDigits() }

fun String.replaceWordsToWordsWithDigits(): String {
    var result = this
    digitsAsWords.keys.forEach { key -> result = result.replace(key, digitsAsWords[key] ?: key) }
    return result
}

val digitsAsWords = mapOf(
    "one" to "one1one",
    "two" to "two2two",
    "three" to "three3three",
    "four" to "four4four",
    "five" to "five5five",
    "six" to "six6six",
    "seven" to "seven7seven",
    "eight" to "eight8eight",
    "nine" to "nine9nine"
)