package `2022`.day2

import utils.FileHandler
import java.io.File

fun main() {
    Day2().main()
}

class Day2 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2022/day2/input.txt"))

    fun main() {
        part2()
    }

    private fun part2() {
        // opponent : A for Rock, B for Paper, and C for Scissors
        // you : X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
        // you : X for Rock, Y for Paper, and Z for Scissors

        var point = 0
        var total = 0
        for (line in lines) {
            val opponent = line[0]
            val input = line[2]
            var you = 'x'

            if (input == 'Y') {
                you = when (opponent) {
                    'A' -> {
                        'X'
                    }

                    'B' -> {
                        'Y'
                    }

                    else -> {
                        'Z'
                    }
                }
            } else if (input == 'X') {
                if (opponent == 'A') {
                    you = 'Z'
                } else if (opponent == 'B') {
                    you = 'X'
                } else {
                    you = 'Y'
                }
            } else {
                if (opponent == 'A') {
                    you = 'Y'
                } else if (opponent == 'B') {
                    you = 'Z'
                } else {
                    you = 'X'
                }
            }

            if (opponent == 'A' && you == 'X' || opponent == 'B' && you == 'Y' || opponent == 'C' && you == 'Z') {
                point += 3
            } else if (opponent == 'A' && you == 'Y' || opponent == 'B' && you == 'Z' || opponent == 'C' && you == 'X') {
                point += 6
            } else {
                point += 0
            }

            if (you == 'X') {
                point += 1
            } else if (you == 'Y') {
                point += 2
            } else {
                point += 3
            }

            println("point...:" + point)
            total += point
            point = 0
        }
        println("total...:" + total)
    }

    private fun part1() {
        // opponent : A for Rock, B for Paper, and C for Scissors
        // you : X for Rock, Y for Paper, and Z for Scissors
        // 1 for Rock, 2 for Paper, and 3 for Scissors

        var point = 0
        for (line in lines) {
            val opponent = line[0]
            val you = line[2]

            if (opponent == 'A' && you == 'X' || opponent == 'B' && you == 'Y' || opponent == 'C' && you == 'Z') {
                point += 3
            } else if (opponent == 'A' && you == 'Y' || opponent == 'B' && you == 'Z' || opponent == 'C' && you == 'X') {
                point += 6
            } else {
                point += 0
            }

            if (you == 'X') {
                point += 1
            } else if (you == 'Y') {
                point += 2
            } else {
                point += 3
            }
        }
        println("point...:" + point)
    }
}
