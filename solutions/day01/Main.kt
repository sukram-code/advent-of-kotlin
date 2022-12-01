package day01

import common.inputLines
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    val elves = inputLines("01", args[0])
        .toElves()
        .sortedByDescending { it.calories }
    println("Top 1 Elf has ${elves[0].calories} calories")
    println("Top 3 Elves have ${elves.take(3).sumOf { it.calories }} calories")
}

data class Elf(val calories: Int)

fun List<String>.toElves(): List<Elf> {
    val elfs = mutableListOf<Elf>()
    var items = mutableListOf<Int>()
    for (line in this) {
        if (line.isEmpty()) {
            elfs.add(Elf(items.sum()))
            items = mutableListOf()
        } else {
            items.add(line.toInt())
        }

        if (indexOf(line) == this.size - 1) {
            elfs.add(Elf(items.sum()))
        }
    }
    return elfs
}