@file:Suppress("DuplicatedCode")

package mmxxi.day9

import java.io.File
import java.io.InputStream
import java.util.*

val map = LinkedHashMap<Int, List<Int>>()

fun main() {
    val file = File("src/main/kotlin/mmxxi/day9/input.txt")

    val inputStream: InputStream = file.inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    for (rowNo in 0 until lineList.size) {
        val row = LinkedList<Int>()
        for (i in 0 until lineList[rowNo].length) {
            row.add(Character.getNumericValue(lineList[rowNo][i]))
        }
        map[rowNo] = row
    }

    val lowPoints = TreeSet<Point>()
    for (rowNo in 0 until map.keys.size)
        for (idx in 0 until (map[rowNo]?.size ?: 0)) {
            val point = Point(rowNo, idx)
            if (isLowPoint(point)) lowPoints.add(point)
        }

//    println(map)
    println(lowPoints)

    var basinSizes = LinkedList<Int>()
    var risk = 0
    for (lowPoint in lowPoints) {
        risk += (map[lowPoint.rowNo]?.get(lowPoint.idx) ?: 0) + 1
        val basin = TreeSet<Point>()
        basin.add(lowPoint)
        findBasin(lowPoint, basin)
//        println("$lowPoint found $basin")

        basinSizes.add(basin.size)

        for (rowNo in 0 until map.keys.size) {
            val row = map[rowNo]
            for (idx in row?.indices!!) {
                val point = Point(rowNo, idx)
                if (basin.contains(point)) print("x") else print(row?.get(idx) ?: "")
            }
            println()
        }
        println()
    }

    var sortSizes = basinSizes.sortedDescending()
    println("basinSizes..: $sortSizes")

    var result = sortSizes[0] * sortSizes[1] * sortSizes[2]

    println("result...: $result")
    println("Risk...: $risk")
}

private fun isLowPoint(point: Point): Boolean {
    val value = map[point.rowNo]?.get(point.idx) ?: 10
    val above = if (point.rowNo > 0) map[point.rowNo - 1]?.get(point.idx) ?: 10 else 10
    val below = map[point.rowNo + 1]?.get(point.idx) ?: 10
    val before = if (point.idx > 0) map[point.rowNo]?.get(point.idx - 1) ?: 10 else 10
    val after = if (point.idx + 1 < map[point.rowNo]?.size!!) map[point.rowNo]?.get(point.idx + 1) ?: 10 else 10
//    println("$value $above $below $before $after")
    return value < above && value < below && value < before && value < after
}

private fun findBasin(point: Point, basin: TreeSet<Point>) {
    // Test above
    if (point.rowNo > 0 && (map[point.rowNo - 1]?.get(point.idx) ?: 10) < 9) {
        val foundPoint = Point(point.rowNo - 1, point.idx)
        if (!basin.contains(foundPoint)) {
            basin.add(foundPoint)
            findBasin(foundPoint, basin)
        }
    }
    // Test below
    if (point.rowNo < map.keys.size - 1 && (map[point.rowNo + 1]?.get(point.idx) ?: 10) < 9) {
        val foundPoint = Point(point.rowNo + 1, point.idx)
        if (!basin.contains(foundPoint)) {
            basin.add(foundPoint)
            findBasin(foundPoint, basin)
        }
    }
    // Test right
    if (point.idx + 1 < (map[point.rowNo]?.size ?: 0) && (map[point.rowNo]?.get(point.idx + 1) ?: 10) < 9) {
        val foundPoint = Point(point.rowNo, point.idx + 1)
        if (!basin.contains(foundPoint)) {
            basin.add(foundPoint)
            findBasin(foundPoint, basin)
        }
    }
    // Test left
    if (point.idx > 0 && (map[point.rowNo]?.get(point.idx - 1) ?: 10) < 9) {
        val foundPoint = Point(point.rowNo, point.idx - 1)
        if (!basin.contains(foundPoint)) {
            basin.add(foundPoint)
            findBasin(foundPoint, basin)
        }
    }
}

data class Point(
    val rowNo: Int,
    val idx: Int
) : Comparable<Point> {
    override fun compareTo(other: Point) = compareValuesBy(this, other,
        { it.rowNo },
        { it.idx }
    )
}
