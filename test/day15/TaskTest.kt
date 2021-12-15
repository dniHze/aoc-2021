package day15

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 15" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day15")) shouldBeExactly 0
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day15")) shouldBeExactly 0
            }
        }
    }
})
