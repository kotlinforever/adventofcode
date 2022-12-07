package mmxxi.day20

import utils.FileHandler
import java.io.File
import java.util.*

fun main() {
//    val day20 = Day20()
//    val decimal = "110110000".toInt(2)
//    println(day20.getNewValue(decimal))
    Day20().main()

}

class Day20 {
    //    val imageEnhancementAlgorithm =
//        "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#"
    private val imageEnhancementAlgorithm =
        "#.#.#.#.#......#.#.#.#.##..#.##.##..#..##...#.#.#.#...##.##.##.###....#..#...#.#..###.#...#..##.#.###..#..####.###...#.#.#..##..##.##..##..###..#....#.#....#####.#...###...#.#....###...#..##.##..#..#.##..###..#.##.###..#.####...#.##.....#.###...#.##.##.#.#######...#.###..##..##..#.#.#.#####...#....#.....##.#.#...##.######....#..#......#.#.#.#.##...######.#.#####..#####..#.#.#.#.###.#.#....#..##..#..#.#.#..##....##..#.#.......##...#..####.####.#.#..#.###..#...#......###...#...#.##.#.####..#.#....###.####..#."

    private var table = LinkedList<MutableList<String>>()

    fun main() {
        initTable()
        printTable(table)

        for (x in 0..1)
            handleTable()

        table.removeFirst()
        table.removeLast()

        for (y in 0 until table.size) {
            table[y][0] = "."
            table[y][table[0].size-1] = "."
        }
        printTable(table)
    }

    private fun getNewValue(no: Int): String {
        return imageEnhancementAlgorithm.substring(no, no + 1)
    }

    private fun handleTable() {
        val newTable = LinkedList<MutableList<String>>()
        for (y in 0 until table.size) {
            val row = arrayListOf<String>()
            for (x in 0 until table[0].size) {
                val binary = getString(Pair(x, y))
                val decimal = binary.toInt(2)
                row.add(getNewValue(decimal))
            }
            newTable.add(row)
        }
        table = newTable
        printTable(table)
    }

    private fun printTable(aTable: MutableList<MutableList<String>>) {
        var count = 0
        for (element in aTable) {
            for (x in 0 until element.size) {
                print(element[x])
                if (element[x] == "#") count++
            }
            println()
        }
        println("count...: $count\n")
    }

    private fun getString(pair: Pair<Int, Int>): String {
        val x = pair.first
        val y = pair.second
        var res = ""

        if (y == 0) res += "000"
        else {
            res += if (x == 0) "0" else convert(table[y - 1][x - 1])
            res += convert(table[y - 1][x])
            res += if (x > table[0].size - 2) "0" else convert(table[y - 1][x + 1])
        }

        res += if (x == 0) "0" else convert(table[y][x - 1])
        res += convert(table[y][x])
        res += if (x > table[0].size - 2) "0" else convert(table[y][x + 1])

        if (y > table.size - 2) res += "000"
        else {
            res += if (x == 0) "0" else convert(table[y + 1][x - 1])
            res += convert(table[y + 1][x])
            res += if (x > table[0].size - 2) "0" else convert(table[y + 1][x + 1])
        }
        return res
    }

    private fun convert(input: String): String {
        return if (input == ".") "0" else "1"
    }

    private fun initTable() {
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", "#", ".", ".", "#", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", "#", ".", ".", ".", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", "#", "#", ".", ".", "#", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", "#", ".", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", "#", "#", "#", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."))
//        table.add(mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."))

//        table.add(mutableListOf(".", ".", "."))
//        table.add(mutableListOf("#", ".", "."))
//        table.add(mutableListOf(".", "#", "."))

        val lines = FileHandler().getLinesAsString(File("src/main/kotlin/mmxxi/day20/input-2.txt"))
        for (line in lines) {
            val row = arrayListOf<String>()
            for (c in line) {
                row.add(c.toString())
            }
            table.add(row)
        }
    }
}
