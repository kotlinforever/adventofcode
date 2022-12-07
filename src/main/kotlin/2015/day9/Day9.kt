@file:Suppress("PackageName", "unused")

package `2015`.day9

import utils.FileHandler
import java.io.File

fun main() {
    Day9().main()
}

private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day9/input.txt"))
//private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day9/test"))

class Day9 {
    fun main() {
        for (line in lines) {
            println(line)
        }
    }
}
