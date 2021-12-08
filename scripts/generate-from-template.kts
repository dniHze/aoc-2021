#!/usr/bin/env kotlin

import java.time.LocalDate
import java.io.File
import kotlin.io.readText
import kotlin.io.writeText
import java.net.HttpURLConnection
import java.net.URL

val now = java.time.LocalDate.now()
val finish = LocalDate.of(2021, 12, 25)
val dayToGenerate = if (now > finish) finish.dayOfMonth else now.dayOfMonth
val packageName = "day" + (if (dayToGenerate < 10) "0" else "") + dayToGenerate

File("src/${packageName}").mkdir()
File("test/${packageName}").mkdir()

File("src/$packageName", "Task.kt").apply {
    createNewFile()
    writeText(
        File("template", "task.kt.txt")
            .readText()
            .replace("{{day_package}}", packageName)
    )
}

File("src/$packageName", "input.txt").apply {
    createNewFile()
    downloadInput(this, dayToGenerate)
}

File("test/$packageName", "TaskTest.kt").apply {
    createNewFile()
    writeText(
        File("template", "test.kt.txt")
            .readText()
            .replace("{{day_package}}", packageName)
            .replace("{{day}}", dayToGenerate.toString())
    )
}

File("test/$packageName", "test_input.txt").apply {
    createNewFile()
    writeText("REPLACE ME")
}

fun downloadInput(
    file: File,
    day: Int,
) {
    with(URL("https://adventofcode.com/2021/day/${day}/input").openConnection() as HttpURLConnection) {
        requestMethod = "GET"
        setRequestProperty("Cookie", "session=${java.lang.System.getenv()["SESSION_COOKIE"]}")
        file.outputStream().use { output ->
            inputStream.use { input ->
                input.copyTo(output)
            }
        }
    }
}