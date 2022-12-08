package day06

import common.inputLines
import common.stopIfInvalid

fun main(args: Array<String>) {
    stopIfInvalid(args)
    val input = inputLines("06", args[0])[0]
    println(firstMarker(input))
    println(firstMarker(input, 14))
}

fun firstMarker(signal: String, marker: Int = 4): Int {
    var lastChars = signal.slice(0 until marker)
    if (lastChars.toCharArray().distinct().size == marker) {
        return marker
    }
    for (i in marker until signal.length) {
        lastChars = lastChars.slice(1 until marker)
        lastChars += signal[i]
        if (lastChars.toCharArray().distinct().size == marker) {
            return i + 1
        }
    }
    return -1
}
