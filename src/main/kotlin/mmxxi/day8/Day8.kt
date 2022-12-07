package mmxxi.day8

import java.io.File
import java.io.InputStream

fun main() {
    val file = File("src/main/kotlin/mmxxi/day8/input.txt")

    val inputStream: InputStream = file.inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var count = 0
    for (line in lineList) {
        val output = line.split((" | "))[1].split(" ")
        for (signal in output) {
            if (signal.length == 2 || signal.length == 3 || signal.length == 4 || signal.length == 7) count++
        }
    }
    println("count..: $count")
}
