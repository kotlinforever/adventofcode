@file:Suppress("PackageName", "unused")

package `2015`.day8

import utils.FileHandler
import java.io.File

fun main() {
//    Day8().main()
    Day8Part2().main()
}

private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day8/input.txt"))
//private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day8/test"))

class Day8Part2 {

    fun main() {
        var res = 0
        for (line in lines) {
            var tmpLine = line.replace("\\", "\\\\").replace("\"", "\\\"")

            tmpLine = "\"${tmpLine}\""
            res += tmpLine.length - line.length
        }
        println("res..: $res")
    }
}

fun String.decodeHex() = ByteArray(this.length / 2) { this.substring(it * 2, it * 2 + 2).toInt(16).toByte() }

class Day8 {
    fun main() {
        var numberOfCharacters = 0
        var charactersInMemory = 0
        for (line in lines) {
            numberOfCharacters += line.length
            var tmpLine = line.substring(1, line.length - 1).replace("\\\"", "@").replace("\\\\", "@")
            tmpLine = remove(tmpLine)

            charactersInMemory += tmpLine.length
        }

        println("numberOfCharacters...: $numberOfCharacters")
        println("charactersInMemory...: $charactersInMemory")
        println("res...: ${numberOfCharacters - charactersInMemory}")
    }

    fun remove(input: String): String {
        var x = input
        while (x.contains("\\x")) {
            val idx = x.indexOf("\\x")
            val hex = x.substring(idx + 2, idx + 4)
            x = x.substring(0, idx) + String(hex.decodeHex()) + x.substring(idx + 4)
            println(x)
        }
        return x
    }
}
