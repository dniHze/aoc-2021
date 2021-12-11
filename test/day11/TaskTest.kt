package day11

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 11" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day11")) shouldBeExactly 0
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day11")) shouldBeExactly 0
            }
        }
    }
})
