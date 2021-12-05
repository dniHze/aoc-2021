import kotlin.math.max
import kotlin.collections.sumOf
import kotlin.math.abs

fun main() {
    val testInput = readInput("Day05_test")
    check(solveDay5Part1(testInput) == 5)
    check(solveDay5Part2(testInput) == 12)

    val input = readInput("Day05")
    println(solveDay5Part1(input))
    println(solveDay5Part2(input))
}

private fun solveDay5Part1(input: List<String>): Int =
    input.toLineList()
        .filterStraightLines()
        .createLineMatrix()
        .sumAtLeast2LinesOverlap()

private fun solveDay5Part2(input: List<String>): Int =
    input.toLineList()
        .createLineMatrix()
        .sumAtLeast2LinesOverlap()

private fun List<String>.toLineList() = map { string -> string.split(" -> ") }
    .map { (start, end) -> Line(start.toCoordinates(), end.toCoordinates()) }

private fun List<Line>.filterStraightLines() = filter { (start, end) -> start.x == end.x || start.y == end.y }

private fun List<Line>.createLineMatrix() = fold(Coordinates(0, 0)) { acc, line -> max(acc, line.max()) }
    .let { (x, y) -> Array(y + 1) { Array(x + 1) { 0 } } }
    .also { matrix ->
        onEach { line ->
            line.onEachMember { (x, y) ->
                matrix[y][x] += 1
            }
        }
    }

private fun Array<Array<Int>>.sumAtLeast2LinesOverlap() =
    sumOf { row -> row.sumOf { amount -> if (amount > 1) 1L else 0L } }.toInt()


private fun String.toCoordinates() = split(',').let { (x, y) -> Coordinates(x.toInt(), y.toInt()) }

private fun step(start: Int, end: Int) = when {
    start == end -> 0
    start < end -> 1
    else -> -1
}


private fun max(first: Coordinates, second: Coordinates) =
    Coordinates(max(first.x, second.x), max(first.y, second.y))


private data class Line(
    val start: Coordinates,
    val end: Coordinates,
)

private data class Coordinates(val x: Int, val y: Int)

private fun Line.onEachMember(block: (Coordinates) -> Unit) {
    val horizontalStep = step(start.x, end.x)
    val verticalStep = step(start.y, end.y)
    repeat(stepsCount()) { count ->
        block(Coordinates(start.x + horizontalStep * count, start.y + verticalStep * count))
    }
}

private fun Line.stepsCount() =
    max(abs(end.x - start.x), abs(end.y - start.y)) + 1

private fun Line.max() = max(start, end)
