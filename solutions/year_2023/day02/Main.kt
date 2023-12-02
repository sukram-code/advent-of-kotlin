package year_2023.day02

import common.inputLines
import common.printIt
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    val lines = inputLines(year = 2023, dayId = "02", fileName = args[0])
    solvePartOne(lines)
    solvePartTwo(lines)
}

fun solvePartOne(lines: List<String>) {
    "Part 1:".printIt()
    lines
        .map { it.toGame() }
        .filter { it.isPossible() }
        .sumOf { it.id }
        .printIt()
}

fun solvePartTwo(lines: List<String>) {
    "Part 2".printIt()
    lines
        .map { it.toGame() }
        .sumOf { it.toPowerOfSetOfCubes() }
        .printIt()
}

fun String.toGame(): Game {
    val idAndSets = this.split(":")
    val id = idAndSets[0].filter { it.isDigit() }.toInt()
    val sets = idAndSets[1].trim().split(";")
    val gameSets = sets.map { set ->
        val colorsToNumbers = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        val moves = set.split(",")
        moves.forEach { move ->
            val numberAndColor = move.trim().split(" ")
            colorsToNumbers[numberAndColor[1]] = colorsToNumbers[numberAndColor[1]]!! + numberAndColor[0].toInt()
        }
        GameSet(colorsToNumbers["red"]!!, colorsToNumbers["green"]!!, colorsToNumbers["blue"]!!)
    }
    return Game(id, gameSets)
}

fun Game.isPossible(gameBag: GameBag = GameBag(red = 12, green = 13, blue = 14)): Boolean {
    sets.forEach { set ->
        if (set.red > gameBag.red || set.green > gameBag.green || set.blue > gameBag.blue) {
            return false
        }
    }
    return true
}

fun Game.toPowerOfSetOfCubes(): Int =
    sets.maxBy { it.red }.red * sets.maxBy { it.green }.green * sets.maxBy { it.blue }.blue

data class Game(val id: Int, val sets: List<GameSet>)
data class GameSet(val red: Int, val green: Int, val blue: Int)

typealias GameBag = GameSet