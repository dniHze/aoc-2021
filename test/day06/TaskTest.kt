package day06

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.longs.shouldBeExactly
import readTestInput

class TaskTest: FreeSpec({

    "day 6" - {
        "part 1" - {
            "should be equal to test data" {
                solvePartOne(readTestInput("day06")) shouldBeExactly 5934L
            }
        }

        "part 2" - {
            "should be equal to test data" {
                solvePartTwo(readTestInput("day06")) shouldBeExactly 26984457539L
            }
        }
    }
})