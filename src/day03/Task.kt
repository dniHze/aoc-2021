package day03

import readInput
import kotlin.math.pow

fun main() {
    val input = readInput("Day03")
    println(solvePartOne(input))
    println(solvePartTwo(input))
}

fun solvePartOne(input: List<String>): Int {
    val counterArray = IntArray(input.first().length) { 0 }
    input.onEach { value ->
        value.reversed().forEachIndexed { index, char ->
            if (char == '1') {
                counterArray[index] += 1
            }
        }
    }
    var gamma = 0
    var epsilon = 0
    counterArray.forEachIndexed { index, count ->
        val positive = (count * 2) > input.size
        if (positive) {
            gamma += (2.0).pow(index).toInt()
        } else {
            epsilon += (2.0).pow(index).toInt()
        }
    }
    return gamma * epsilon
}

fun solvePartTwo(input: List<String>): Int {
    val testTree = input.toWeightedTree()
    val scrubberRating = testTree.findSmallestNode()
    val generatorRating = testTree.findBiggestNode()
    return scrubberRating * generatorRating
}

private fun List<String>.toWeightedTree(): TreeNode {
    val tree = TreeNode(parent = null, value = "")
    onEach { item ->
        var node = tree
        item.forEach { char ->
            node = if (char == '1') node.right else node.left
        }
        node.increaseWeight()
    }
    return tree
}

private fun TreeNode.findBiggestNode(): Int {
    var node = this
    var binaryString = ""
    while (node.biggest != null) {
        node = node.biggest!!
        binaryString += node.value
    }
    return binaryString.toInt(2)
}

private fun TreeNode.findSmallestNode(): Int {
    var node = this
    var binaryString = ""
    while (node.smallest != null) {
        node = node.smallest!!
        binaryString += node.value
    }
    return binaryString.toInt(2)
}

data class TreeNode(
    val value: String,
    val parent: TreeNode?,
) {
    private var weight = 0

    val left by lazy { TreeNode(value = "0", parent = this) }
    val right by lazy { TreeNode(value = "1", parent = this) }

    val smallest: TreeNode?
        get() = when {
            left.weight == 0 && right.weight == 0 -> null
            left.weight == 0 -> right
            right.weight == 0 -> left
            left <= right -> left
            else -> right
        }

    val biggest: TreeNode?
        get() = when {
            left.weight == 0 && right.weight == 0 -> null
            left.weight == 0 -> right
            right.weight == 0 -> left
            right >= left -> right
            else -> left
        }

    operator fun compareTo(other: TreeNode) = weight.compareTo(other.weight)

    fun increaseWeight() {
        weight++
        parent?.increaseWeight()
    }
}
