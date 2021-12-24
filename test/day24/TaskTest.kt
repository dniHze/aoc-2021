package day24

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 24" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day24")) shouldBeExactly 0
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day24")) shouldBeExactly 0
            }
        }
    }
})
