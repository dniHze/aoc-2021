fun main() {
    val testInput = readInput("Day01_test")
    check(solveTask1(testInput) == 8)
    check(solveTask2(testInput) == 6)

    val input = readInput("Day01")
    println(solveTask1(input))
    println(solveTask2(input))
}

fun solveTask1(input: List<String>): Int =
    input.map { value -> value.toInt() }
        .solveIncreasingTimes()

fun solveTask2(input: List<String>): Int = input.map { value -> value.toInt() }
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
