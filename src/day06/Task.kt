package day06

import readInput

fun main() {
    val input = readInput("day06")
    println(solvePartOne(input))
    println(solvePartTwo(input))
}

fun solvePartOne(input: List<String>): Long = input.createFishColony()
    .processDays(80)
    .count()

fun solvePartTwo(input: List<String>): Long = input.createFishColony()
    .processDays(256)
    .count()

private fun List<String>.createFishColony() = flatMap { it.split(',') }
    .filter { value -> value.isNotEmpty() }
    .map { value -> value.toInt() }
    .groupBy { value -> value }
    .mapValues { (_, values) -> values.size.toLong() }

private fun FishColony.processDays(count: Int) = (0 until count)
    .fold(this) { colony, _ -> processADay(colony) }

private fun processADay(input: FishColony): FishColony = buildMap {
    for ((key, value) in input) {
        if (key > 0) {
            this[key - 1] = value + (this[key - 1] ?: 0)
        } else {
            this[8] = value
            this[6] = value + (this[6] ?: 0)
        }
    }
}

private fun FishColony.count() = map { (_, values) -> values }.sum()

private typealias FishColony = Map<Int, Long>
