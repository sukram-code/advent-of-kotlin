package year_2022.day02

import common.inputLines
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    val lines = inputLines(dayId = "02", fileName = args[0])
    println(lines.toRoundsByMove().sumOf { it.score })
    println(lines.toRoundsByOutcome().sumOf { it.score })
}

fun List<String>.toRoundsByMove(): List<MoveRound> {
    return this
        .map { it.split(" ") }
        .map { MoveRound(Move.villain(it[0]), Move.hero(it[1])) }
}

fun List<String>.toRoundsByOutcome(): List<OutcomeRound> {
    return this
        .map { it.split(" ") }
        .map { OutcomeRound(Move.villain(it[0]), Outcome.fromValue(it[1])) }
}

data class MoveRound(val villain: Move, val hero: Move) {
    val score = hero.versus(villain).score + hero.score
}

data class OutcomeRound(val villain: Move, val outcome: Outcome) {
    val score = outcome.score + outcome.against(villain).score
}

interface Versus {
    fun versus(move: Move): Outcome
}

enum class Move(
    private val villainValue: String,
    private val heroValue: String,
    val score: Int
): Versus {
    Rock("A", "X", 1) {
        override fun versus(move: Move): Outcome {
            return when (move) {
                Scissors -> Outcome.Win
                Paper -> Outcome.Lose
                else -> Outcome.Draw
            }
        }
    },
    Paper("B", "Y", 2) {
        override fun versus(move: Move): Outcome {
            return when (move) {
                Rock -> Outcome.Win
                Scissors -> Outcome.Lose
                else -> Outcome.Draw
            }
        }
    },
    Scissors("C", "Z", 3) {
        override fun versus(move: Move): Outcome {
            return when (move) {
                Paper -> Outcome.Win
                Rock -> Outcome.Lose
                else -> Outcome.Draw
            }
        }
    };

    companion object {
        fun villain(move: String): Move = values().first { it.villainValue == move }
        fun hero(move: String): Move = values().first { it.heroValue == move }
    }
}

interface Against {
    fun against(move: Move): Move
}

enum class Outcome(val score: Int, val value: String): Against {
    Win(6, "Z") {
        override fun against(move: Move): Move {
            return when(move) {
                Move.Rock -> Move.Paper
                Move.Paper -> Move.Scissors
                Move.Scissors -> Move.Rock
            }
        }
    },
    Lose(0, "X") {
        override fun against(move: Move): Move {
            return when(move) {
                Move.Rock -> Move.Scissors
                Move.Paper -> Move.Rock
                Move.Scissors -> Move.Paper
            }
        }
    },
    Draw(3, "Y") {
        override fun against(move: Move): Move {
            return when(move) {
                Move.Rock -> Move.Rock
                Move.Paper -> Move.Paper
                Move.Scissors -> Move.Scissors
            }
        }
    };

    companion object {
        fun fromValue(value: String): Outcome = values().first { it.value == value }
    }
}
