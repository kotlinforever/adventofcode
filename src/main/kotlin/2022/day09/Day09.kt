package `2022`.day09

import utils.FileHandler
import java.io.File

fun main() {
    Day09().main()
}

/**
 * steps...: 2347
 * steps...: 2347
 * steps...: 2159 is too low
 * steps...: 2347
 * steps...: 2584 is too low
 */
@Suppress("DuplicatedCode")
class Day09 {

    val mapPart1 = mutableMapOf<Pair<Int, Int>, Boolean>()
    val listPart2 = mutableListOf<SnakePart>()

    fun main() {
//        part1("src/main/kotlin/2022/day09/input.txt")

//        part2(FileHandler().getLinesAsString(File("src/main/kotlin/2022/day09/input")), 2) // 13
//        part2(FileHandler().getLinesAsString(File("src/main/kotlin/2022/day09/test-part2.txt")), 10) // 36
        part2(FileHandler().getLinesAsString(File("src/main/kotlin/2022/day09/input2.txt")), 10)
    }

    private fun part2(lines: MutableList<String>, noOfSnakes: Int) {

        for (i in 0 until noOfSnakes ) {
            listPart2.add(SnakePart("Snake-$i"))
        }

        for (line in lines) {
            println(line)
            val direction = line.split(" ")[0]
            val steps = line.split(" ")[1].toInt()
            for (i in 0 until steps) {
                move(direction, listPart2[0])
                for (j in 0 until noOfSnakes-1) {
                    moveAfter(listPart2[j], listPart2[j + 1])
                }
            }
        }

        for (snake in listPart2) {
            println(snake)
        }
        println("\nsteps...: ${listPart2[noOfSnakes-1].steps.size}")
    }

    private fun print() {
        for (snake in listPart2) {
            for (i in 0..10) {
                print(i)
            }
            println(snake.name)
        }
    }

    private fun moveAfter(head: SnakePart, tail: SnakePart) {
        if (head.x - 2 == tail.x) {
            tail.x = tail.x + 1
            tail.y = head.y
        }
        if (head.x + 2 == tail.x) {
            tail.x = tail.x - 1
            tail.y = head.y
        }
        if (head.y - 2 == tail.y) {
            tail.y = tail.y + 1
            tail.x = head.x
        }
        if (head.y + 2 == tail.y) {
            tail.y = tail.y - 1
            tail.x = head.x
        }
        tail.steps = tail.steps.plus(Pair(tail.x, tail.y))
    }

    private fun move(direction: String, snakePart: SnakePart) {
        when (direction) {
            "R" -> snakePart.x = snakePart.x + 1
            "L" -> snakePart.x = snakePart.x - 1
            "U" -> snakePart.y = snakePart.y + 1
            "D" -> snakePart.y = snakePart.y - 1
        }
        snakePart.steps = snakePart.steps.plus(Pair(snakePart.x, snakePart.y))
    }

    private fun part1(file: String) {
        val lines = FileHandler().getLinesAsString(File(file))

        var headX = 100
        var headY = 100

        var tailX = 100
        var tailY = 100

        addToMap(tailX, tailY)

        for (line in lines) {
            val direction = line.split(" ")[0]
            val steps = line.split(" ")[1].toInt()
            print(steps)

            for (i in 0 until steps) {
                print(".")
                when (direction) {
                    "R" -> {
                        headX++
                        if (headX - 2 == tailX) {
                            tailX++
                            tailY = headY
                        }
                    }

                    "L" -> {
                        headX--
                        if (headX + 2 == tailX) {
                            tailX--
                            tailY = headY
                        }
                    }

                    "U" -> {
                        headY++
                        if (headY - 2 == tailY) {
                            tailY++
                            tailX = headX
                        }
                    }

                    "D" -> {
                        headY--
                        if (headY + 2 == tailY) {
                            tailY--
                            tailX = headX
                        }
                    }
                }
                addToMap(tailX, tailY)
            }
        }

        println("map.size...: ${mapPart1.keys}")
        println("map.size...: ${mapPart1.size}")
    }

    fun addToMap(x: Int, y: Int) {
        val pair = Pair(x, y)
        if (!mapPart1.containsKey(pair)) {
            mapPart1[pair] = true
        }
    }
}

data class SnakePart(
    val name: String = "",
    var x: Int = 0,
    var y: Int = 0,
    var steps: Set<Pair<Int, Int>> = mutableSetOf()
) {
    constructor(name: String) : this(name, 0, 0, mutableSetOf())
}