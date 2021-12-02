fun main() {
    val testInput = readInput("Day02_test")
    check(testInput.solveDay2(aim = false) == 150)
    check(testInput.solveDay2(aim = true) == 900)

    val input = readInput("Day02")
    println(input.solveDay2(aim = false))
    println(input.solveDay2(aim = true))
}

private fun List<String>.solveDay2(aim: Boolean): Int = toCommandList()
    .fold(Point(0, 0, 0)) { point, command ->
        command(point, aim)
    }
    .let { (x, y) -> x * y }

private fun List<String>.toCommandList() = map { value ->
    value.split(' ').run { get(0) to get(1).toInt() }
}.map { (direction, value) ->
    when (direction) {
        "forward" -> horizontalCommand(value)
        "up" -> verticalCommand(-value)
        "down" -> verticalCommand(value)
        else -> noneCommand
    }
}

data class Point(
    val x: Int,
    val y: Int,
    val aim: Int,
)


private typealias Command = (value: Point, aim: Boolean) -> Point

private val noneCommand: Command = { value, _ -> value }

private fun horizontalCommand(delta: Int): Command = { point, aim ->
    if (aim) {
        point.copy(
            x = point.x + delta,
            y = point.y + point.aim * delta,
        )
    } else {
        point.copy(x = point.x + delta)
    }
}

private fun verticalCommand(delta: Int): Command = { point, aim ->
    if (aim) {
        point.copy(aim = point.aim + delta)
    } else {
        point.copy(y = point.y + delta)
    }
}