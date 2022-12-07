@file:Suppress("PackageName", "unused")

package `2015`.day6

import utils.FileHandler
import java.io.File
import java.util.*

fun main() {
//    Day6().main()
    Day6Part2().main()
}

class Day6Part2 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day6/input.txt"))
    private var table = LinkedList<MutableList<Int>>()

    fun main() {
        setupTable()

        val turnOn = { i: Int, j: Int ->
            table[i][j] = table[i][j] + 1
        }

        val turnOff = { i: Int, j: Int ->
            var value = table[i][j]
            if (value > 1)
                value -= 1
            else
                value = 0
            table[i][j] = value
        }

        val toggle = { i: Int, j: Int ->
            table[i][j] = table[i][j] + 2
        }

        for (line in lines) {
            val lineSplit = line.split(" ")
            if (lineSplit[0] == "turn")
                if (lineSplit[1] == "on")
                    change(turnOn, lineSplit[2], lineSplit[4])
                else
                    change(turnOff, lineSplit[2], lineSplit[4])
            else
                change(toggle, lineSplit[1], lineSplit[3])
        }


        var no = 0
        for (row in table)
            for (cell in row)
                no += cell

        println("no..: $no")
    }

    private fun change(lmbd: (Int, Int) -> Unit, start: String, end: String) {
        val s = getPair(start)
        val e = getPair(end)

        for (i in s.first..e.first) {
            for (j in s.second..e.second) {
                lmbd(i, j)
            }
        }
    }

    private fun getPair(input: String): Pair<Int, Int> {
        val lineSplit = input.split(",")
        return Pair(lineSplit[0].toInt(), lineSplit[1].toInt())
    }

    private fun setupTable() {
        val line = mutableListOf<Int>()
        for (i in 0..999) {
            line.add(0)
        }

        for (i in 0..999) {
            table.add(line.toMutableList())
        }
    }
}

class Day6 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day6/input.txt"))
    private var table = LinkedList<MutableList<Int>>()

    fun main() {
        setupTable()

        val turnOn = { i: Int, j: Int ->
            table[i][j] = 1
        }

        val turnOff = { i: Int, j: Int ->
            table[i][j] = 0
        }

        val toggle = { i: Int, j: Int ->
            table[i][j] = if (table[i][j] == 0) 1 else 0
        }

        for (line in lines) {
            val lineSplit = line.split(" ")
            if (lineSplit[0] == "turn")
                if (lineSplit[1] == "on")
                    change(turnOn, lineSplit[2], lineSplit[4])
                else
                    change(turnOff, lineSplit[2], lineSplit[4])
            else
                change(toggle, lineSplit[1], lineSplit[3])
        }


        var no = 0
        for (row in table)
            for (cell in row)
                no += cell

        println("no..: $no")
    }

    private fun change(lmbd: (Int, Int) -> Unit, start: String, end: String) {
        val s = getPair(start)
        val e = getPair(end)

        for (i in s.first..e.first) {
            for (j in s.second..e.second) {
                lmbd(i, j)
            }
        }
    }

    private fun getPair(input: String): Pair<Int, Int> {
        val lineSplit = input.split(",")
        return Pair(lineSplit[0].toInt(), lineSplit[1].toInt())
    }

    private fun setupTable() {
        val line = mutableListOf<Int>()
        for (i in 0..999) {
            line.add(0)
        }

        for (i in 0..999) {
            table.add(line.toMutableList())
        }
    }
}
