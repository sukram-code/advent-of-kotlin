package day03

import common.inputLines
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    val lines = inputLines("03", args[0])
    val totalPriority = lines
        .map { it.toRucksack() }
        .map { it.first.filter { item -> it.second.contains(item) }.toCharArray().distinct() }
        .flatten()
        .sumOf { it.toPriority() }
    println(totalPriority)

    val totalSafePriority = lines
        .windowed(3, 3)
        .map { it[0].filter { item -> it[1].contains(item) && it[2].contains(item) }.toCharArray().distinct() }
        .flatten()
        .sumOf { it.toPriority() }
    println(totalSafePriority)
}

fun String.toRucksack(): Pair<String, String> {
    val middle: Int = this.length / 2
    val firstCompartment = this.slice(0 until middle)
    val secondCompartment = this.slice(middle until this.length)
    return Pair(firstCompartment, secondCompartment)
}

private const val LOWERCASE = 96
private const val UPPERCASE = 38

fun Char.toPriority(): Int {
    return when {
        isUpperCase() -> code - UPPERCASE
        isLowerCase() -> code - LOWERCASE
        else -> throw IllegalArgumentException()
    }
}