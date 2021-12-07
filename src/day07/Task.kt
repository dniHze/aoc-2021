package day07

import readInput
import kotlin.math.max
import kotlin.collections.sumOf
import kotlin.math.abs
import kotlin.math.min

fun main() {
    val input = readInput("day07")
    println(solvePartOne(input))
    println(solvePartTwo(input))
}

fun solvePartOne(input: List<String>): Int = input.toInput()
    .minValue { position -> linearConsumption(position) }

fun solvePartTwo(input: List<String>): Int = input.toInput()
    .minValue { position -> progressiveConsumption(position) }


private fun List<String>.toInput() = first().split(',').map { value -> value.toInt() }

private fun List<Int>.linearConsumption(position: Int) = sumOf { value -> abs(position - value) }

private fun List<Int>.progressiveConsumption(position: Int) = sumOf { value ->
    val stepsCount = abs(position - value)
    (stepsCount + 1) * stepsCount / 2
}

private fun List<Int>.minValue(
    block: List<Int>.(position: Int) -> Int,
): Int {
    var maxValue = Int.MIN_VALUE
    var minValue = Int.MAX_VALUE
    onEach { value ->
        maxValue = max(maxValue, value)
        minValue = min(minValue, value)
    }
    return recursiveBinarySearch(
        start = minValue,
        end = maxValue,
    ) { index -> block(index) }
}

private fun recursiveBinarySearch(
    start: Int,
    end: Int,
    selector: (position: Int) -> Int
): Int {
    if (start == end) return selector(start)
    val startSelector = selector(start)
    val endSelector = selector(end)
    val middle = (start + end) / 2
    return when {
        startSelector < endSelector -> recursiveBinarySearch(
            start = start,
            end = middle,
            selector = selector
        )
        else -> recursiveBinarySearch(
            start = if (start == middle) middle + 1 else middle,
            end = end,
            selector = selector
        )
    }
}
