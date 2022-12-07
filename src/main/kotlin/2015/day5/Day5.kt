@file:Suppress("PackageName", "unused")

package `2015`.day5

import utils.FileHandler
import java.io.File

fun main() {
//    Day5().testCode()
//    Day5().main()

//    Day5Part2().testCode()
    Day5Part2().main()
}

class Day5Part2 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day5/input.txt"))

    fun testCode() {
        var input = "qjhvhtzxzqqjkmpb"
        println(testInput(input))

        input = "xxyxx"
        println(testInput(input))

        input = "uurcxstgmygtbstg"
        println(testInput(input))

        input = "ieodomkazucvgmuy"
        println(testInput(input))
    }

    fun main() {
        var nice = 0
        for (line in lines) {
            if (testInput(line)) nice++
        }
        println("nice..: $nice")
    }

    private fun testInput(input: String): Boolean {
        return testPair(input) && testRepeatLetter(input)
    }

    private fun testRepeatLetter(input: String): Boolean {
        var s1 = 0
        var s2 = 2

        while (s2 < input.length) {
            if (input[s1] == input[s2]) return true
            s1++
            s2++
        }
//        println("testRepeatLetter..: $input")
        return false
    }

    private fun testPair(input: String): Boolean {
        val pairs = mutableSetOf<String>()
        var pastLetter = ""
        var prePastPair = ""
        var pastPair = ""
        input.forEach {
            val pair = pastLetter + it
            pastLetter = it.toString()
            if (pair.length == 2) {
                if (prePastPair == pair || (pastPair != pair && pairs.contains(pair))) {
                    return true
                }
                prePastPair = pastPair
                pastPair = pair
                pairs.add(pair)
            }
        }
//        println("testPair..: $input")
        return false
    }
}

class Day5 {

    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/2015/day5/input.txt"))

    fun main() {
        var nice = 0
        for (line in lines) {
            if (testInput(line)) nice++
        }
        println("nice..: $nice")
    }

    fun testCode() {
        var input = "ugknbfddgicrmopn"
        assert(testInput(input))

        input = "aaa"
        assert(testInput(input))

        input = "jchzalrnumimnmhp"
        assert(!testInput(input))

        input = "haegwjzuvuyypxyu"
        assert(!testInput(input))

        input = "dvszwmarrgswjxmb"
        assert(!testInput(input))
    }

    private fun testInput(input: String): Boolean {
        return testVowels(input) && testDoubleLetter(input) && testNotContain(input)
    }

    private fun testNotContain(input: String): Boolean {
        val testFor = arrayListOf("ab", "cd", "pq", "xy")
        for (test in testFor) {
            if (input.contains(test)) {
                println("testNotContain..: $input")
                return false
            }
        }
        return true
    }

    private fun testDoubleLetter(input: String): Boolean {
        var last = '_'
        input.forEach {
            if (it == last) return true else last = it
        }
        println("testDoubleLetter..: $input")
        return false
    }

    private fun testVowels(input: String): Boolean {
        var no = 0
        val testFor = arrayListOf('a', 'e', 'i', 'o', 'u')
        input.forEach {
            if (testFor.contains(it)) no++
        }
        return if (no >= 3)
            true
        else {
            println("testVowels..: $input")
            false
        }
    }
}
