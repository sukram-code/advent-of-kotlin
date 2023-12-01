package year_2022.day04

import common.inputLines
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    val elfPairs = inputLines(dayId = "04", fileName = args[0])
        .toElfPairs()

    val fullOverlaps = elfPairs
        .count { it.first.fullOverlaps(it.second) || it.second.fullOverlaps(it.first) }
    val anyOverlaps = elfPairs
        .count { it.first.overlapsAnywhere(it.second) || it.second.overlapsAnywhere(it.first) }
    println("Full overlaps: $fullOverlaps")
    println("Any overlaps: $anyOverlaps")
}

fun List<String>.toElfPairs(): List<Pair<Elf, Elf>> {
    return this.map { it.split(",") }
        .map {
            val firstElf = it.get(0).split("-")
            val secondElf = it.get(1).split("-")
            Pair(
                Elf(firstElf[0].toInt(), firstElf[1].toInt()),
                Elf(secondElf[0].toInt(), secondElf[1].toInt())
            )
        }
}

data class Elf(val start: Int, val end: Int) {
    fun fullOverlaps(other: Elf): Boolean = start <= other.start && end >= other.end
    fun overlapsAnywhere(other: Elf): Boolean = (start >= other.start && start <= other.end)
            || (end >= other.start && end <= other.end)
}
