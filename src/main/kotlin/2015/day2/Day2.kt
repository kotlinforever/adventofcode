package `2015`.day2

import utils.FileHandler
import java.io.File

fun main() {
    Day2part2().main()
}

class Day2part2 {

    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day2/input.txt"))
//    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day2/test"))

    fun main() {
        var sum = 0
        for (line in lines) {
            val lineSplit = line.split("x")
            val l = lineSplit[0].toInt()
            val w = lineSplit[1].toInt()
            val h = lineSplit[2].toInt()

            val s1 = l + l
            val s2 = w + w
            val s3 = h + h
            val array = listOf(s1, s2, s3).sorted()
            sum += array[0] + array[1] + l * w * h
        }
        println("sum...:${sum}")
    }
}

class Day2 {

    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day2/input.txt"))
//    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day2/input.txt"))

    fun main() {
        var sum = 0
        for (line in lines) {
            val lineSplit = line.split("x")
            val l = lineSplit[0].toInt()
            val w = lineSplit[1].toInt()
            val h = lineSplit[2].toInt()

            val s1 = l * w
            val s2 = w * h
            val s3 = h * l
            sum += 2 * s1 + 2 * s2 + 2 * s3 + listOf(s1, s2, s3).sorted()[0]
        }
        println("sum...:${sum}")
    }
}
