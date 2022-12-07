@file:Suppress("PackageName", "unused")

package `2015`.day7

import utils.FileHandler
import java.io.File

fun main() {
    Day7().main()
}

class Day7 {
    //    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day7/test"))
//    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day7/input.txt"))
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day7/input-part-2.txt"))

    private var map = mutableMapOf<String, Int>()

    fun main() {
        while (lines.isNotEmpty()) {
            println("lines size...: ${lines.size}")
            if (lines.size == 1)
                println("TEST")

            val newList = lines.toMutableList()
            for (line in lines) {
                if (calculate(line)) {
                    newList.remove(line)
                }
            }
            lines = newList.toMutableList()
        }

        for (m in map.toSortedMap())
            println("$m")

        println("Result ${map["a"]}")
    }

    private fun calculate(line: String): Boolean {
        val splitValues = line.split(" -> ")
        val key = splitValues[1]

        val rightSide = splitValues[0].split(" ")

        try {
            if (rightSide.size == 1) {
                map[key] = getValue(rightSide[0])
            } else if (rightSide.size == 2) {
                val value = map[rightSide[1]]
                if (value != null)
                    map[key] = getNotValue(value)
                else
                    throw IllegalArgumentException()
            } else if (rightSide.size == 3) {
                when (rightSide[1]) {
                    "AND" -> map[key] = getValue(rightSide[0]) and getValue(rightSide[2])
                    "OR" -> map[key] = getValue(rightSide[0]) or getValue(rightSide[2])
                    "LSHIFT" -> map[key] = getValue(rightSide[0]) shl rightSide[2].toInt()
                    "RSHIFT" -> map[key] = getValue(rightSide[0]) shr rightSide[2].toInt()
                    else -> {
                        println("TIS not known")
                    }
                }
            }
        } catch (e: RuntimeException) {
            return false
        }

        return true
    }

    private fun getValue(key: String): Int {
        if (map.containsKey(key))
            return map[key]!!

        return key.toInt()
    }

    private fun getNotValue(input: Int): Int {
        val bit = Integer.toBinaryString(input).padStart(Short.SIZE_BITS, '0')

        var newBit = ""
        for (c in bit) {
            newBit += if (c == '0') 1 else 0
        }

        return newBit.toInt(2)
    }
}


//d: 72
//e: 507
//f: 492
//g: 114
//h: 65412
//i: 65079
//x: 123
//y: 456
