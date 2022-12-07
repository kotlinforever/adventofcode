package mmxxi.day9

import java.io.File
import java.io.InputStream
import java.util.*

fun main() {
    val file = File("src/main/kotlin/mmxxi/day9/input.txt")

    val inputStream: InputStream = file.inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val map = LinkedHashMap<Int, List<Int>>()

    for (rowNo in 0 until lineList.size) {
        val row = LinkedList<Int>()
        for (i in 0 until lineList[rowNo].length) {
            row.add(Character.getNumericValue(lineList[rowNo][i]))
        }
        map[rowNo] = row
    }

    val lowPoints = LinkedList<Int>()
    for (rowNo in 0 until map.keys.size)
        for (idx in 0 until (map[rowNo]?.size ?: 0))
            isLowPoint(map, rowNo, idx, lowPoints)

//    println(map)
    println(lowPoints)

    var risk = 0
    for (lowPoint in lowPoints)
        risk += lowPoint + 1

    println("Risk...: $risk")
}

private fun isLowPoint(
    map: LinkedHashMap<Int, List<Int>>,
    rowNo: Int,
    idx: Int,
    lowPoints: LinkedList<Int>
) {
    val value = map[rowNo]?.get(idx) ?: 10
    val above = if (rowNo > 0) map[rowNo - 1]?.get(idx) ?: 10 else 10
    val below = map[rowNo + 1]?.get(idx) ?: 10
    val before = if (idx > 0) map[rowNo]?.get(idx - 1) ?: 10 else 10
    val after = if (idx + 1 < map[rowNo]?.size!!) map[rowNo]?.get(idx + 1) ?: 10 else 10
    println("$value $above $below $before $after")
    if (value < above && value < below && value < before && value < after) {
        lowPoints.add(value)
    }
}
