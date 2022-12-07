package `2015`.day1

import java.io.File

fun main() {
    Day1().main()
}

class Day1() {

    fun main() {
        val fileContent = File("src/main/kotlin/2015/day1/input.txt").readText()

        var no = 0
        var floor = 0
        for (element in fileContent) {
            if (element == '(')
                floor++
            else if (element == ')')
                floor--

            no++
            if (floor == -1) {
                println("Hit basement at position $no")
            }
        }
        println("End floor..: $floor")
    }
}
