package `2022`.day1

import utils.FileHandler
import java.io.File

fun main() {
    Day1().main()
}

class Day1 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2022/day1/input.txt"))

    fun main() {
        var sum = 0
        val list = mutableListOf<Int>()
        for (line in lines) {
            if (line == "") {
                list.add(sum)
                sum = 0
            } else {
                sum += line.toInt()
            }
        }
        list.sort()
        val x = list[list.size-1] + list[list.size - 2] + list[list.size - 3]

        println("list..:${list}")
        println("x..:${x}")
    }
}
