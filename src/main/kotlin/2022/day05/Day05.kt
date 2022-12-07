package `2022`.day05

import utils.FileHandler
import java.io.File

fun main() {
    Day05().main()
}

class Day05 {

    fun main() {
//        val stacks = init()
//        for (stack in stacks) {
//            println(stack)
//        }
//        part2(stacks, "src/main/kotlin/2022/day05/input")

        val stacks = initPart1("src/main/kotlin/2022/day05/input-input.txt")
        for (stack in stacks) {
            println(stack)
        }
        part2(stacks, "src/main/kotlin/2022/day05/input.txt")


    }

    private fun init(): ArrayList<ArrayDeque<Char>> {
        val stacks = ArrayList<ArrayDeque<Char>>()
        for (i in 0 until 3) {
            stacks.add(ArrayDeque<Char>())
        }

        stacks[0].add('Z')
        stacks[0].add('N')
        stacks[1].add('M')
        stacks[1].add('C')
        stacks[1].add('D')
        stacks[2].add('P')

        return stacks
    }

    private fun initPart1(file: String): ArrayList<ArrayDeque<Char>> {
        val stacks = ArrayList<ArrayDeque<Char>>()
        for (i in 0 until 9) {
            stacks.add(ArrayDeque())
        }

        val lines = FileHandler().getLinesAsString(File(file))

        val xs = listOf(1, 5, 9, 13, 17, 21, 25, 29, 33)
        for (line in lines) {
            for ((i, x) in xs.withIndex()) {
                val c: Char = line[x]
                if (c != ' ') {
                    stacks[i].addFirst(line[x])
                }
            }
        }
        return stacks
    }

    private fun part1(stacks: ArrayList<ArrayDeque<Char>>, file: String) {
        val lines = FileHandler().getLinesAsString(File(file))

        for (line in lines) {
            println("**********************")
            println(line)
            val split = line.split(" ")
            val no = split[1].toInt()
            val from = split[3].toInt()
            val to = split[5].toInt()
            println("no..: $no")
            println("from..: $from")
            println("to..: $to")

            for (i in 0 until no) {
                val last = stacks[from - 1].removeLast()
                stacks[to - 1].add(last)
            }
        }

        var x = ""
        for (stack in stacks) {
            x += stack.last()
        }
        println(x)

    }

    private fun part2(stacks: ArrayList<ArrayDeque<Char>>, file: String) {
        val lines = FileHandler().getLinesAsString(File(file))

        for (line in lines) {
            println("**********************")
            println(line)
            val split = line.split(" ")
            val no = split[1].toInt()
            val from = split[3].toInt()
            val to = split[5].toInt()
            println("no..: $no")
            println("from..: $from")
            println("to..: $to")

            val tmp = ArrayDeque<Char>()
            for (i in no downTo 1) {
                val last = stacks[from - 1].removeLast()
                tmp.add(last)
            }
            for (x in tmp.reversed()) {
                stacks[to - 1].add(x)
            }
        }

        var x = ""
        for (stack in stacks) {
            println(stack)
            x += stack.last()
        }
        println(x)

    }
}
