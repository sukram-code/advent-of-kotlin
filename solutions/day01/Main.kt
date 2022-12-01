package day01

import common.inputLines
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    val elves = inputLines("01", args[0])
        .toElves()
        .sortedByDescending { it.sumOfCalories() }
    println("Top 1 Elf has ${elves.take(1).sumOf { it.sumOfCalories() }} calories")
    println("Top 3 Elves have ${elves.take(3).sumOf { it.sumOfCalories() }} calories")
}

data class Elf(val calories: List<Int>) {
    fun sumOfCalories() = calories.sum()
}

fun List<String>.toElves(): List<Elf> {
    val elfs = mutableListOf<Elf>()
    var items = mutableListOf<Int>()
    for (line in this) {
        if (line.isEmpty()) {
            elfs.add(Elf(items))
            items = mutableListOf()
        } else {
            items.add(line.toInt())
        }

        if (indexOf(line) == this.size - 1) {
            elfs.add(Elf(items))
        }
    }
    return elfs
}