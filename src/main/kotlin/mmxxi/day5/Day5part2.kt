package mmxxi.day5

import java.io.File
import java.io.InputStream

fun main() {
    val file = File("src/main/kotlin/mmxxi/day5/input.txt")

    val inputStream: InputStream = file.inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var maxX = 0
    var maxY = 0
    for (line in lineList) {
        val numbers = line.split(" ")
        val x1 = numbers[0].split((","))[0].toInt()
        val x2 = numbers[0].split((","))[1].toInt()
        val y1 = numbers[2].split((","))[0].toInt()
        val y2 = numbers[2].split((","))[1].toInt()

        if (x1 == y1 || x2 == y2) {
            if (x1 > maxX) maxX = x1
            if (x2 > maxX) maxX = x2
            if (y1 > maxY) maxY = y1
            if (y2 > maxY) maxY = y2
        }
    }
    println("maxX..: $maxX")
    println("maxY..: $maxY")


    val map = LinkedHashMap<Int, MutableList<Int>>()
    for (x in 0..maxX) {
        val list = MutableList(maxY + 1) { 0 }
        map.put(x, list.toMutableList())
    }

    for (line in lineList) {
        val numbers = line.split(" ")
        val x1 = numbers[0].split((","))[0].toInt()
        val y1 = numbers[0].split((","))[1].toInt()
        val x2 = numbers[2].split((","))[0].toInt()
        val y2 = numbers[2].split((","))[1].toInt()

        if (x2 - x1 == y2 - y1) { // \
            println("1:$line\t$x1\t$y1\t$x2\t$y2")
            if (y2 > y1) {
                var startX = 0
                if (x2 > x1) startX = x1 else startX = x2
                for (r in y1..y2) {
                    val row = map[r]
                    row?.set(startX, row[startX] + 1)
                    startX++
                }
            } else {
                var startX = 0
                if (x2 > x1) startX = x1 else startX = x2
                for (r in y2..y1) {
                    val row = map[r]
                    row?.set(startX, row[startX] + 1)
                    startX++
                }
            }
        } else if (x1 - x2 == y2 - y1) { // /
            println("3:$line\t$x1\t$y1\t$x2\t$y2")
            var startX = 0
            var startY = 0
            var no = 0
            if (x2 > x1) startX = x2 else startX = x1
            if (y2 > y1) {
                startY = y1
                no = y2 - y1
            } else {
                startY = y2
                no = y1 - y2
            }
            println("startX...: $startX")
            println("startY...: $startY")
            println("no.......: $no")

            for (r in startY..startY + no) {
                val row = map[r]
                row?.set(startX, row[startX] + 1)
                startX--
            }
        } else if (x1 == x2) {
//            println("$line\t$x1\t$y1\t$x2\t$y2")
            if (y1 < y2)
                for (y in y1..y2) {
                    val row = map[y]
                    row?.set(x1, row[x1] + 1)
                }
            else
                for (y in y2..y1) {
                    val row = map[y]
                    row?.set(x1, row[x1] + 1)
                }
        } else if (y1 == y2) {
            println("$line\t$x1\t$y1\t$x2\t$y2")
            val row = map[y1]
            if (x1 < x2)
                for (x in x1..x2)
                    row?.set(x, row[x] + 1)
            else
                for (x in x2..x1)
                    row?.set(x, row[x] + 1)
        }
    }

    var no = 0
    map.forEach {
        println(it)
        it.value.forEach { number ->
            if (number > 1) no++
        }
    }
    println("Result....: $no")

}
