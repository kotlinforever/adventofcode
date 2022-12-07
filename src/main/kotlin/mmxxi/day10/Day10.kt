package mmxxi.day10

import java.io.File
import java.io.InputStream
import java.util.*

val startMap = mapOf<Char, Char>('[' to ']', '{' to '}', '<' to '>', '(' to ')')
val endMap = mapOf<Char, Char>(']' to '[', '}' to '{', '>' to '<', ')' to '(')
val pointsMap = mapOf<Char, Int>(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)

fun main() {
    val file = File("src/main/kotlin/mmxxi/day10/input.txt")

    val inputStream: InputStream = file.inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var illegalCharacter = mutableListOf<Char>()
    for (line in lineList) {
        val stack = ArrayDeque<Char>()
        for (c in line) {
//            println("'$c'")
            if (startMap.containsKey(c)) stack.push(c)
            else if (endMap.containsKey(c) && stack.peek() == endMap[c]) stack.pop()
            else {
                illegalCharacter.add(c)
                break
            }
        }
    }

    var result = 0
    for (item in illegalCharacter.distinct()) {
        result += ((pointsMap[item]!!) * Collections.frequency(illegalCharacter, item))
    }
    println(result)

}

