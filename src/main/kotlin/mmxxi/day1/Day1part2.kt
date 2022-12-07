package mmxxi.day1

import utils.FileHandler
import java.io.File

fun main() {
    Day1part2().main()
}

class Day1part2 {
    val file = File("src/main/kotlin/mmxxi/day1/input.txt")

    fun main() {
        val lineList = FileHandler().getLinesAsInt(file)

        var increased = 0
        var oldSum = Int.MAX_VALUE
        for (no in 0 until lineList.size) {
            if (no + 2 < lineList.size) {
                val sum = lineList[no] + lineList[no + 1] + lineList[no + 2]
                if (sum > oldSum) increased++
                oldSum = sum
            }
        }

        println("res..: $increased")
    }
}
