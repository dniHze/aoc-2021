package day01

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 1" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day01")) shouldBeExactly 8
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day01")) shouldBeExactly 6
            }
        }
    }
})