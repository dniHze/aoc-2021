package day01

import readInput

fun main() {
    val input = readInput("day01")
    println(solvePartOne(input))
    println(solvePartTwo(input))
}

fun solvePartOne(input: List<String>): Int =
    input.map { value -> value.toInt() }
        .solveIncreasingTimes()

fun solvePartTwo(input: List<String>): Int = input.map { value -> value.toInt() }
    .windowed(size = 3, step = 1, partialWindows = false) { list ->
        list.sum()
    }
    .solveIncreasingTimes()


internal fun Iterable<Int>.solveIncreasingTimes(): Int = fold(Result()) { acc, value ->
    val newCounter = when {
        acc.previousHighest != null && value > acc.previousHighest -> acc.counter + 1
        else -> acc.counter
    }
    acc.copy(previousHighest = value, counter = newCounter)
}.counter

data class Result(
    val previousHighest: Int? = null,
    val counter: Int = 0,
)
