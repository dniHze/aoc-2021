package day02

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 2" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day02")) shouldBeExactly 150
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day02")) shouldBeExactly 900
            }
        }
    }
})