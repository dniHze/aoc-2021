package day04

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 4" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day04")) shouldBeExactly 4512
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day04")) shouldBeExactly 1924
            }
        }
    }
})