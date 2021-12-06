package day05

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 5" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day05")) shouldBeExactly 5
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day05")) shouldBeExactly 12
            }
        }
    }
})