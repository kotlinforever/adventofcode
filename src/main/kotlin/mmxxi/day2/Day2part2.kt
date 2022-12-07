package mmxxi.day2

import utils.FileHandler
import java.io.File
import java.io.InputStream

fun main() {
    Day2part2().main()
}

class Day2part2 {
    fun main() {
        val file = File("src/main/kotlin/mmxxi/day2/input.txt")
        val lineList = FileHandler().getLinesAsString(file)

        var aim = 0
        var horizontal = 0
        var depth = 0

        for (x in 0 until lineList.size) {
            val string = lineList[x]
            if (string.startsWith("up")) {
                val i = string.substring("up".length).trim().toInt()
                aim -= i
//            println("up..: $i")
            } else if (string.startsWith("down")) {
                val i = string.substring("down".length).trim().toInt()
                aim += i
//            println("down..: $i")
            } else if (string.startsWith("forward")) {
                val i = string.substring("forward".length).trim().toInt()
                horizontal += i
                depth += aim * i
//            println("forward..: $i")
            } else
                println("FUCK")
        }
        println("horizontal...: $horizontal")
        println("depth........: $depth")

        println("res...: ${horizontal * depth}")
    }
}
