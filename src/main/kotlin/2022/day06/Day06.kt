package `2022`.day06

import utils.FileHandler
import java.io.File

fun main() {
    Day06().main()
}

class Day06 {

    fun main() {
        // test
//        val lines = FileHandler().getLinesAsString(File("src/main/kotlin/2022/day06/input"))
//        for (line in lines) {
//            println(part1(line))
//        }

        // part 1
        var text = File("src/main/kotlin/2022/day06/input.txt").readText(Charsets.UTF_8)
        println(part1(text, 3))

        // part 2
        text = File("src/main/kotlin/2022/day06/input.txt").readText(Charsets.UTF_8)
        println(part1(text, 13))
    }

    private fun part1(text: String, size: Int): Int {

        val stack = ArrayDeque<Char>()
        for ((no, c) in text.toCharArray().withIndex()) {
            stack.add(c)
            if (no >= size) {
                val set = HashSet<Char>()
                for (charInStack in stack) {
                    set.add(charInStack)
                }
                if (set.size > size) {
                    return no + 1
                }
                stack.removeFirst()
            }
        }
        return 0
    }
}
