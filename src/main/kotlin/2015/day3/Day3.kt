@file:Suppress("PackageName")

package `2015`.day3

import utils.FileHandler

fun main() {
//    Day1().main()
    Day1part2().main()
}

class Day1part2 {

    private var uniqHouses = 1
    private var visitedHouses = mutableSetOf<Pair<Int, Int>>()

    fun main() {
        val fileContent = FileHandler().readFileAsTextUsingInputStream("src/main/kotlin/2015/day3/input.txt")

        var santa = Pair(0, 0)
        var robotSanta = Pair(0, 0)

        visitedHouses.add(Pair(0, 0))

        var idx = 0
        for (c in fileContent.toCharArray()) {
            if (idx++ % 2 == 0)
                santa = cal(c, santa)
            else
                robotSanta = cal(c, robotSanta)
        }

        println("uniqHouses..: $uniqHouses")
    }

    fun cal(c: Char, pair: Pair<Int, Int>): Pair<Int, Int> {
        var x = pair.first
        var y = pair.second
        when (c) {
            '^' -> x++
            'v' -> x--
            '>' -> y++
            '<' -> y--
        }
        val newPair = Pair(x, y)
        if (!visitedHouses.contains(newPair)) {
            uniqHouses++
            visitedHouses.add(newPair)
        }
        return newPair
    }
}

// 2565
@Suppress("unused")
class Day1 {

    private var uniqHouses = 1
    private var visitedHouses = mutableSetOf<Pair<Int, Int>>()

    fun main() {
        val fileContent = FileHandler().readFileAsTextUsingInputStream("src/main/kotlin/2015/day3/input.txt")
//        val fileContent = ">" // 2
//        val fileContent = "^>v<" // 4
//        val fileContent = "^v^v^v^v^v" // 2

        var x = 0
        var y = 0
        visitedHouses.add(Pair(x, y))
        for (c in fileContent.toCharArray()) {
            when (c) {
                '^' -> x++
                'v' -> x--
                '>' -> y++
                '<' -> y--
                else -> {
                    break
                }
            }
            val pair = Pair(x, y)
            if (!visitedHouses.contains(pair)) {
                uniqHouses++
                visitedHouses.add(pair)
            }
        }

        println("uniqHouses..: $uniqHouses")
    }
}
