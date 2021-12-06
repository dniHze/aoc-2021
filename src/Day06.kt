fun main() {
    val testInput = readInput("Day06_test")
    check(solveDay6Part1(testInput) == 5934L)
    check(solveDay6Part2(testInput) == 26984457539L)

    val input = readInput("Day06")
    println(solveDay6Part1(input))
    println(solveDay6Part2(input))
}

private fun solveDay6Part1(input: List<String>): Long = input.createFishColony()
    .processDays(80)
    .count()

private fun solveDay6Part2(input: List<String>): Long = input.createFishColony()
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
