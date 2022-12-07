@file:Suppress("PackageName")

package `2015`.day4

import java.math.BigInteger
import java.security.MessageDigest

fun main() {
    Day4().main()
}

// 2565
@Suppress("unused")
class Day4 {

    fun main() {
        val input = "iwrupvqb"
        var res = md5(input)

        var answer = 0
        while (!res.startsWith("000000")) {
            res = md5(input + ++answer)
        }
        println("input....: ${input + answer}")
        println("answer...: $answer")
        println("output...: $res")
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}
