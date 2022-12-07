package mmxxi.day3

import java.io.File
import java.io.InputStream

fun main() {

    val file = File("src/main/kotlin/mmxxi/day3/input.txt")

    val inputStream: InputStream = file.inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var first = ""
    for (x in 0 until lineList[0].length) {
        first += getVal(lineList, x)
    }
    println(first)
    val fDecimap = first.toInt(2)
    println(fDecimap)

    var second = ""
    for (element in first) {
        second += inverst(element)
    }
    println(second)
    val sDecimap = second.toInt(2)
    println(sDecimap)

    println("Result...:${fDecimap * sDecimap}")
}

fun inverst(str: Char): String {
    return if (str == '0') "1" else "0"
}

fun getVal(lineList: List<String>, row: Int): String {
    var zeros = 0
    var ones = 0

    for (element in lineList) {
        if (Character.getNumericValue(element[row]) == 0) zeros++ else ones++
    }

    return if (zeros > ones) "0" else "1"
}
