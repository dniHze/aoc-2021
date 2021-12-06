import com.jakewharton.picnic.TableSectionDsl
import com.jakewharton.picnic.TextAlignment
import com.jakewharton.picnic.table
import java.time.LocalDate

fun main() {
    table {
        cellStyle {
            border = true
            alignment = TextAlignment.MiddleRight
        }

        header {
            cellStyle {
                alignment = TextAlignment.MiddleCenter
            }

            row {
                cell("Advent Of Code 2021") {
                    columnSpan = 3
                    paddingLeft = 3
                    paddingRight = 3
                }
            }

            row {
                cellStyle {
                    paddingLeft = 1
                    paddingRight = 1
                }
                cell("#")
                cell("Part 1") {
                    paddingLeft = 3
                    paddingRight = 3
                }
                cell("Part 2")
            }
        }

        body {
            cellStyle {
                paddingLeft = 1
                paddingRight = 1
            }

            generateRows()
        }
    }.also { println(it) }
}

private fun TableSectionDsl.generateRows() {
    val currentDay = LocalDate.now()
    val dayCount = if (currentDay > LocalDate.of(2021, 12, 25)) {
        25
    } else {
        currentDay.dayOfMonth
    }
    repeat(dayCount) { dayIndex ->
        val day = dayIndex + 1
        val packageName = "day" + if (day < 10) "0$day" else day.toString()
        val file = readInput(packageName)
        row {
            cell(day)
            val kClass = Class.forName("$packageName.TaskKt")
            val method1 = kClass.getMethod("solvePartOne", List::class.java)
            val method2 = kClass.getMethod("solvePartTwo", List::class.java)
            cell(method1.invoke(null, file).toString())
            cell(method2.invoke(null, file).toString())
        }
    }
}
