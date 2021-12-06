package day04

import readInput

fun main() {
    val input = readInput("day04")
    println(solvePartTwo(input))
    println(solvePartTwo(input))
}

fun solvePartOne(input: List<String>): Int {
    val winners = calculateBingoWinners(input)
    return winners.first().sum
}

fun solvePartTwo(input: List<String>): Int {
    val winners = calculateBingoWinners(input)
    return winners.last().sum
}

private fun calculateBingoWinners(input: List<String>): List<BingoWinner> {
    val bingoInputs = input.bingoInputList()
    val boards = buildBoards(input)
    val indexes = buildIndexes(boards)
    return buildList<BingoWinner> {
        for (numberString in bingoInputs) {
            val number = numberString.toInt()
            val boardIndexes = indexes[number]
            if (boardIndexes != null) {
                for (boardIndex in boardIndexes) {
                    if (boardIndex.markAndCheck() && none { (index) -> index == boardIndex.board.index }) {
                        val index = boardIndex.board.index
                        val sum = boardIndex.board.calculateUnmarkedSum() * number
                        add(BingoWinner(index, sum))
                    }
                }
            }
        }
    }
}

private fun buildBoards(input: List<String>) = buildList {
    val boardsInputOnly = input.drop(1)
    val tempMatrix = mutableListOf<List<BingoNumber>>()

    boardsInputOnly.forEachIndexed { index, value ->
        val inputs = value.replace(' ', ',')
            .split(',')
            .filter { member -> member.isNotEmpty() }
            .map { member -> member.toInt() }

        if (inputs.isNotEmpty()) {
            tempMatrix += inputs.map { intValue -> BingoNumber(intValue, false) }
        }

        if ((inputs.isEmpty() || index == boardsInputOnly.lastIndex) && tempMatrix.isNotEmpty()) {
            val card = tempMatrix.toList()
            tempMatrix.clear()
            add(card)
        }
    }
}.mapIndexed { index, numbers -> BingoBoard(index, numbers) }

private fun buildIndexes(boards: List<BingoBoard>): Map<Int, List<BingoIndex>> = buildMap {
    boards.forEach { board ->
        board.numbers.forEachIndexed { y, row ->
            row.forEachIndexed { x, (intValue) ->
                val indexList = getOrPut(intValue) { emptyList() }
                this[intValue] = indexList + BingoIndex(board, x, y)
            }
        }
    }
}

private fun List<String>.bingoInputList() = first().splitToSequence(",")

private typealias BingoNumbers = List<List<BingoNumber>>

private data class BingoBoard(
    val index: Int,
    val numbers: BingoNumbers,
)

private fun BingoBoard.markAndCheck(x: Int, y: Int): Boolean {
    get(x, y).marked = true
    return checkRow(y) || checkColumn(x)
}

private fun BingoBoard.get(x: Int, y: Int) = numbers[y][x]
private fun BingoBoard.checkRow(y: Int): Boolean = numbers[y].all { number -> number.marked }
private fun BingoBoard.checkColumn(x: Int): Boolean = numbers.map { it[x] }.all { number -> number.marked }
private fun BingoBoard.calculateUnmarkedSum(): Int = numbers.sumOf { row ->
    row.sumOf { number -> number.value.takeIf { !number.marked } ?: 0 }
}


private data class BingoIndex(
    val board: BingoBoard,
    val x: Int,
    val y: Int,
)

private data class BingoWinner(
    val index: Int,
    val sum: Int,
)

private fun BingoIndex.markAndCheck(): Boolean = board.markAndCheck(x, y)

private data class BingoNumber(
    val value: Int,
    var marked: Boolean,
)
