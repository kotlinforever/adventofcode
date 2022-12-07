package mmxxi.day2

import java.io.File
import java.io.InputStream

fun main() {
    val file = File("src/main/kotlin/mmxxi/day2/input.txt")

    val inputStream: InputStream = file.inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var forward = 0
    var horizontal = 0

    for (x in 0 until lineList.size) {
        val string = lineList[x]
        if (string.startsWith("up")) {
            val i = string.substring("up".length).trim().toInt()
            horizontal -= i
//            println("up..: $i")
        } else if (string.startsWith("down")) {
            val i = string.substring("down".length).trim().toInt()
            horizontal += i
//            println("down..: $i")
        } else if (string.startsWith("forward")) {
            val i = string.substring("forward".length).trim().toInt()
            forward += i
//            println("forward..: $i")
        } else
            println("FUCK")
    }
    println("forward......: $forward")
    println("horizontal...: $horizontal")

    println("Answer.......: ${forward * horizontal}")
}
