package day07

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 7" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day07")) shouldBeExactly 37
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day07")) shouldBeExactly 168
            }
        }
    }
})