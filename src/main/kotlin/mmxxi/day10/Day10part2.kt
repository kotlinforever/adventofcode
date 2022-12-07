package mmxxi.day10

import java.io.File
import java.io.InputStream
import java.util.*

fun main() {
    Day10part2().main()

//    val day = Day10part2()
//    val line = "(({[{[{<(({([{[]{}}(()())]{{()()}<<>()>})([{{}{}}[{}{}]]<<(){}>[()<>]>)}<{{(()[])}}{{[[]()"
//    val stack = day.getIllegalCharacter(line)
//    val points = day.calPoints(stack)
//    println(points)
}

class Day10part2 {

    val startMap = mapOf<Char, Char>('[' to ']', '{' to '}', '<' to '>', '(' to ')')
    val endMap = mapOf<Char, Char>(']' to '[', '}' to '{', '>' to '<', ')' to '(')
    val pointsMap = mapOf<Char, Int>(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    fun main() {
        val file = File("src/main/kotlin/mmxxi/day10/input.txt")

        val inputStream: InputStream = file.inputStream()
        val lineList = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine { lineList.add(it) }

        val pointsList = mutableListOf<Long>()
        for (line in lineList) {
            val stack = getIllegalCharacter(line)
            if (!stack.isEmpty()) pointsList.add(calPoints(stack))
        }
        println(pointsList.sorted()[pointsList.size / 2])
    }

    fun getIllegalCharacter(line: String): ArrayDeque<Char> {
        val stack = ArrayDeque<Char>()
        for (c in line) {
            if (startMap.containsKey(c)) stack.push(c)
            else if (endMap.containsKey(c) && stack.peek() == endMap[c]) stack.pop()
            else {
                stack.clear()
                break
            }
        }
        return stack
    }

    fun calPoints(stack: ArrayDeque<Char>): Long {
        var points = 0L

        val ending = mutableListOf<Char>()
        while (!stack.isEmpty()) ending.add(startMap[stack.pop()]!!)

        for (c in ending) {
            points *= 5
            points += pointsMap[c]!!
        }
//        println("ok : $line : $ending : $points")
        return points
    }
}

