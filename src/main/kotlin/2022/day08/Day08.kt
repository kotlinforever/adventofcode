package `2022`.day08

import utils.FileHandler
import java.io.File

fun main() {
    Day08().main()
}

class Day08 {

    val rows = mutableListOf<List<Int>>()

    fun main() {
//        part1("src/main/kotlin/2022/day07/input")
        part2("src/main/kotlin/2022/day08/input")
    }

    private fun part2(file: String) {
        val lines = FileHandler().getLinesAsString(File(file))
        for (line in lines) {
            val row = mutableListOf<Int>()
            for (char in line.toCharArray()) {
                row.add(char.digitToInt())
            }
            rows.add(row)
        }

        var res = 0
        for ((rno, row) in rows.withIndex()) {
            val y = mutableListOf<Int>()
            for ((no, r) in row.withIndex()) {

                if (!(rno == 0 || rno == rows.size - 1 || no == 0 || no == row.size - 1)) {

                    var count = 0
                    var x = no
                    while (x > 0) {
                        if (r <= row[--x]) {
                            x = 0
                        }
                        count++
                    }
                    y.add(count)

                    x = no
                    count = 0
                    while (x < row.size - 1) {
                        if (r <= row[++x]) {
                            x = row.size + 10
                        }
                        count++
                    }
                    y.add(count)

                    x = rno
                    count = 0
                    while (x > 0) {
                        if (r <= rows[--x][no]) {
                            x = 0
                        }
                        count++
                    }
                    y.add(count)

                    x = rno
                    count = 0
                    while (x < rows.size - 1) {
                        if (r <= rows[++x][no]) {
                            x = rows.size + 10
                        }
                        count++
                    }
                    y.add(count)

                    val scenicScore = y[0] * y[1] * y[2] * y[3]
                    if (scenicScore > res) {
                        res = scenicScore
                    }
                    println("$r:$y")
                    y.clear()
                }
            }
        }
        println(res)
    }

    private fun part1(file: String) {
        val lines = FileHandler().getLinesAsString(File(file))
        for (line in lines) {
            val row = mutableListOf<Int>()
            for (char in line.toCharArray()) {
                row.add(char.digitToInt())
            }
            rows.add(row)
        }

        var edge = 0
        var trees = 0
        for ((rno, row) in rows.withIndex()) {
            for ((no, r) in row.withIndex()) {
//                print(r)
                if (rno == 0 || rno == rows.size - 1 || no == 0 || no == row.size - 1) {
                    edge++
                } else {
                    var visible = false
//                    print("\n$r:")
                    var x = no
                    var v = true
                    while (x > 0) {
                        if (r <= row[--x]) {
                            v = false
                        }
                    }
                    if (v) {
                        visible = true
                    }

                    x = no
                    v = true
                    while (x < row.size - 1) {
                        if (r <= row[++x]) {
                            v = false
                        }
                    }
                    if (v) {
                        visible = true
                    }

                    x = rno
                    v = true
                    while (x > 0) {
                        if (r <= rows[--x][no]) {
                            v = false
                        }
                    }
                    if (v) {
                        visible = true
                    }

                    x = rno
                    v = true
                    while (x < rows.size - 1) {
//                        print("${rows[++x][no]}")
                        if (r <= rows[++x][no]) {
                            v = false
                        }
                    }
                    if (v) {
                        visible = true
                    }

                    if (visible) {
                        println("tree..:rows[$rno][$no]  $r")
                        trees++
                    }
                }

            }
        }

        println("\nsum...:$edge")
        println("trees...:$trees")

        println("res...:${edge + trees}")
    }
}
