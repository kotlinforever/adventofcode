package mmxxi.day4


import java.io.File
import java.io.InputStream

fun main() {

    val numbers =
        mutableListOf(
            18,99,39,89,0,40,52,72,61,77,69,51,30,83,20,65,93,88,29,22,14,82,53,41,76,79,46,78,56,57,24,36,38,11,50,1,19,26,70,4,54,3,84,33,15,21,9,58,64,85,10,66,17,43,31,27,2,5,95,96,16,97,12,34,74,67,86,23,49,8,59,45,68,91,25,48,13,28,81,94,92,42,7,37,75,32,6,60,63,35,62,98,90,47,87,73,44,71,55,80
        ).map { it.toString() }
    val file = File("src/main/kotlin/mmxxi/day4/input.txt")
//    val numbers =
//        mutableListOf(
//            7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1
//        ).map { it.toString() }
//    val file = File("src/main/kotlin/mmxxi/day4/input")

    val inputStream: InputStream = file.inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val map = LinkedHashMap<Int, MutableList<List<String>>>()

    extractBoards(lineList, map)

//    println("Plader...: ${map.keys.size}")
//    for (x in 0 until map.keys.size) {
//        println("$x ${map[x]?.size}")
//    }

    val drawnNumbers = mutableListOf<String>()
    for (element in numbers) {
        drawnNumbers.add(element)
        val winner = checkBingo(drawnNumbers, map)
        if (winner != null) {
            val winnerBoard = map[winner]
            var sum = 0
            map[winner]?.forEach {
                it.forEach { number ->
                    if (!drawnNumbers.contains(number)) sum += number.toInt()
                }
            }
            println("final sum..: $sum")
            println("Result.....: ${sum * element.toInt()}")
            return
        }
    }
}

fun checkBingo(drawnNumbers: MutableList<String>, map: java.util.LinkedHashMap<Int, MutableList<List<String>>>): Int? {
    map.forEach {
        val key = it.key
        it.value.forEach { row ->
//             println("$key  ${row}")
            if (drawnNumbers.containsAll(row)) {
                println("BINGO board $key")
                return key
            }
        }
    }
    return null
}


private fun extractBoards(lineList: MutableList<String>, map: LinkedHashMap<Int, MutableList<List<String>>>) {
    var i = 0
    val lines = mutableListOf<List<String>>()
    for (x in 0 until lineList.size) {
        val str = lineList[x].trim()
        if (str.isEmpty()) {
            map[i] = lines.toMutableList()
            i++
            lines.clear()
            continue
        }
        val separate2 = str.split(" ")
        val array = arrayListOf<String>()
        separate2.filterTo(array) { it.isNotEmpty() }
        lines.add(array)
    }
    map[i] = lines
}
