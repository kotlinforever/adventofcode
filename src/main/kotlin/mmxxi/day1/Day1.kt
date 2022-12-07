import java.io.File
import java.io.InputStream

fun main() {
    val file = File("src/main/kotlin/mmxxi/day1/input.txt")

    val inputStream: InputStream = file.inputStream()
    val lineList = mutableListOf<Int>()
    inputStream.bufferedReader().forEachLine { lineList.add(it.toInt()) }

    var no = 0
    var v: Int? = null
    for (x in 0 until lineList.size) {
        if (v != null && lineList[x] > v) no++
        v = lineList[x]
    }

    println("no..: $no")
}
