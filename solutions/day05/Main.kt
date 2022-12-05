package day05

import common.inputLines
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    val lines = inputLines("05", args[0]).partition { !it.startsWith("move") }
    val reversedLines = lines.first.reversed()
        .map { it.replace("[\\[\\]\\s]".toRegex(), "") }
        .map { it.toCharArray() }
    val stacksOne = stacks(reversedLines)
    val instructions = lines.second.toInstructions()
    instructions.forEach { instruction ->
        for (i in 0 until instruction.move) {
            val item = stacksOne[instruction.from].removeLast()
            stacksOne[instruction.to].addLast(item)
        }
    }
    val stacksTwo = stacks(reversedLines)
    instructions.forEach { instruction ->
        val tmp = ArrayDeque<Char>()
        for (i in 0 until instruction.move)
            tmp.addLast(stacksTwo[instruction.from].removeLast())
        for (i in 0 until instruction.move)
            stacksTwo[instruction.to].addLast(tmp.removeLast())
    }
    println(stacksOne.map { it.removeLast() }.joinToString(""))
    println(stacksTwo.map { it.removeLast() }.joinToString(""))
}

fun List<String>.toInstructions(): List<Instruction> {
    return map { it.replace("\\s".toRegex(), "") }
        .map {
            val values = it.replace("move", "")
                .replace("from|to".toRegex(), " ")
            val instructions = values.split(" ")
            Instruction(
                instructions[0].toInt(),
                instructions[1].toInt() - 1,
                instructions[2].toInt() - 1
            )
        }
}

fun stacks(lines: List<CharArray>): List<ArrayDeque<Char>> {
    val list = mutableListOf<ArrayDeque<Char>>()
    for (i in 0 until lines[0].size) {
        list.add(ArrayDeque())
    }
    lines.forEach { line ->
        line.forEachIndexed { idx, ch ->
            if (ch != '0')
                list[idx].addLast(ch)
        }
    }
    return list
}

data class Instruction(val move: Int, val from: Int, val to: Int)
