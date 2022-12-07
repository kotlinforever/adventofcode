@file:Suppress("PackageName", "unused")

package `2015`.day11

fun main() {
    Day11().main()
}

class Day11 {

    var letters = "abcdefghijklmnopqrstuvwxyz"

    fun main() {
        var str = "xx"
        println(str)

        repeat(3) {
            var letter = str.substring(str.length-1, str.length)
            println(letter)
        }
    }
}
