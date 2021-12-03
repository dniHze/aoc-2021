import kotlin.math.pow

fun main() {
    val testInput = readInput("Day03_test")
    check(testInput.solveDay3Part1() == 198)
    check(testInput.solveDay3Part2() == 230)

    val input = readInput("Day03")
    println(input.solveDay3Part1())
    println(input.solveDay3Part2())
}

private fun List<String>.solveDay3Part1(): Int {
    val counterArray = IntArray(first().length) { 0 }
    onEach { value ->
        value.reversed().forEachIndexed { index, char ->
            if (char == '1') {
                counterArray[index] += 1
            }
        }
    }
    var gamma = 0
    var epsilon = 0
    counterArray.forEachIndexed { index, count ->
        val positive = (count * 2) > size
        if (positive) {
            gamma += (2.0).pow(index).toInt()
        } else {
            epsilon += (2.0).pow(index).toInt()
        }
    }
    return gamma * epsilon
}

private fun List<String>.solveDay3Part2(): Int {
    val testTree = toWeightedTree()
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
