@file:Suppress("PackageName", "unused")

package `2015`.day10

fun main() {
    Day10().main()
}

class Day10 {

    fun main() {
        var str = "3113322113"
        repeat(50) {
            str = cal(str)
        }
        println(str)

//        var str = "1"
//        repeat(5) {
//            str = cal(str)
//            println(str)
//        }
        println("res...: ${str.length}")
    }

    private fun cal(str: String): String {
        var res = ""
        var lastChar: Char? = null
        var no = 1
        for (element in str) {
            if (lastChar == null) {
                lastChar = element
            } else if (element == lastChar) {
                no++
            } else {
                res += no.toString() + lastChar
                lastChar = element
                no = 1
            }
        }
        res += no.toString() + lastChar

        return res
    }
}
