package mmxxi.day13

import utils.FileHandler
import java.io.File
import java.util.*

fun main() {
    Day13().main()
}

class Day13 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/mmxxi/day13/input.txt"))
    private var table = LinkedList<MutableList<String>>()
    private var folds = LinkedList<String>()

    fun main() {
        val (maxX, maxY) = findSize()
        setupTable(maxY, maxX)

        for (fold in folds) {
            val foldNo = fold.split("=")[1].toInt()
            if (fold.split("=")[0] == "y") {
                val newTable = LinkedList<MutableList<String>>()
                if (table.size == foldNo * 2 + 1) {
                    for (rowNo in 0 until foldNo) {
                        val r1 = table[rowNo]
                        val r2 = table[table.size - rowNo - 1]
                        val newRow = arrayListOf<String>()
                        for (x in 0 until r1.size)
                            if (r1[x] == "#" || r2[x] == "#") newRow.add("#") else newRow.add(".")
                        newTable.add(newRow)
                    }
                    table = newTable
                } else if (table.size - foldNo > foldNo + 1)
                    println("Y:Flere efter...")
                else
                    println("Y:Flere før...")
            } else if (fold.split("=")[0] == "x") {
                if (table[0].size == foldNo * 2 + 1) {
                    for (yNo in 0 until table.size) {
                        val newRow = arrayListOf<String>()
                        for (xNo in 0 until foldNo) {
                            if (table[yNo][xNo] == "#" || table[yNo][table[yNo].size - xNo - 1] == "#")
                                newRow.add("#")
                            else
                                newRow.add(".")
                        }
                        table[yNo] = newRow
                    }
                } else if (table[0].size - foldNo > foldNo + 1)
                    println("X:Flere efter...")
                else
                    println("X:Flere før...")
            }
        }

        // Count dots
        var res = 0
        for (l in table) {
            for (x in l)
                if (x == "#") res++
            println()
        }
        println("res..: $res")

        printTable(table)
    }

    private fun setupTable(maxY: Int, maxX: Int) {
        for (x in 0..maxY) {
            val row = arrayListOf<String>()
            for (y in 0..maxX) {
                row.add(".")
            }
            table.add(row)
        }

        for (line in lines) {
            if (line.split((",")).size > 1) {
                val x = line.split((","))[0].toInt()
                val y = line.split((","))[1].toInt()
                table[y][x] = "#"
            } else if (line.startsWith("fold")) {
                folds.add(line.split(" ")[2])
            }
        }
    }

    private fun findSize(): Pair<Int, Int> {
        var maxX = 0
        var maxY = 0
        for (line in lines) {
            if (line.split((",")).size > 1) {
                val x = line.split((","))[0].toInt()
                val y = line.split((","))[1].toInt()
                if (x > maxX) maxX = x
                if (y > maxY) maxY = y
            }
        }
        println("maxX..: $maxX")
        println("maxY..: $maxY")
        return Pair(maxX, maxY)
    }

    private fun printTable(map: LinkedList<MutableList<String>>) {
        for (l in map) {
            for (x in l)
                print(x)
            println()
        }
    }
}
