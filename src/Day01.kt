fun main() {
    val testInput = readInput("Day01_test")
    check(solve(testInput) == 8)

    val input = readInput("Day01")
    println(solve(input))
}

fun solve(input: List<String>): Int =
        input
                .map { value -> value.toInt() }
                .fold(Result()) { acc, value ->
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