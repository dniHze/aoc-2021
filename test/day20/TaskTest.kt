package day20

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 20" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day20")) shouldBeExactly 0
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day20")) shouldBeExactly 0
            }
        }
    }
})
