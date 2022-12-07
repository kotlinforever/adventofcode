package mmxxi.day11

import utils.FileHandler
import utils.Point
import java.io.File
import java.util.*

fun main() {
    Day11().main()
}

class Day11 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/mmxxi/day11/input-mini"))
    private var table = LinkedList<MutableList<Int>>()
    private var flashes = 0

    fun main() {
        setupTable()

        printTable()
        for (x in 0..10)
            flashe()

        println("sum..: $flashes")
    }

    private fun flashe() {
        for (rowNo in 0 until table.size) {
            for (idx in 0 until table[0].size) {
                changeValue(Point(rowNo, idx), false)
            }
        }
        printTable()
    }

    private fun changeAround(rowNo: Int, idx: Int) {
        changeValue(Point(rowNo - 1, idx - 1), true)
        changeValue(Point(rowNo - 1, idx), true)
        changeValue(Point(rowNo - 1, idx + 1), true)

        changeValue(Point(rowNo, idx - 1), true)
        changeValue(Point(rowNo, idx + 2), true)

        changeValue(Point(rowNo + 1, idx - 1), true)
        changeValue(Point(rowNo + 1, idx), true)
        changeValue(Point(rowNo + 1, idx + 1), true)
    }

    private fun changeValue(point: Point, around: Boolean) {
        if (point.rowNo >= 0 && point.rowNo < table.size && point.idx >= 0 && point.idx < table[0].size) {
            var value = table[point.rowNo][point.idx] + 1
            if (value > 9) {
                flashes++
                value = 0
            }
            table[point.rowNo][point.idx] = value

            if (value == 0 && !around) changeAround(point.rowNo, point.idx)
        }
    }

    private fun printTable() {
        println("\nAfter step :")
        for (rowNo in 0 until table.size) {
            for (idx in 0 until table[0].size)
                print(table[rowNo][idx])
            println()
        }
    }

    private fun setupTable() {
        for (line in lines) {
            val row = arrayListOf<Int>()
            for (c in line) {
                row.add(Character.getNumericValue(c))
            }
            table.add(row.toMutableList())
        }
    }
}
