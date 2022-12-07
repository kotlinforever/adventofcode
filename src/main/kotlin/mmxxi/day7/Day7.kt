package mmxxi.day7

import java.io.File

fun main() {

    val fileContent = File("src/main/kotlin/mmxxi/day7/input.txt").readText()

    val strs = fileContent.split(",").toTypedArray()

    var max = 0
    for (str in strs) {
        var strNo = str.trim().toInt()
        if (strNo > max) max = strNo
    }
    println("max...: $max")

    var min = Int.MAX_VALUE
    val map = LinkedHashMap<Int, Int>()
    for (x in 0..max) {
        val res = calculate(x, strs)
        map[x] = res
        if (res < min) min = res
    }
    println("Min steps...: $min")

//    map.forEach {
//        println("${it.key}    ${it.value}")
//    }
}

private fun calculate(x: Int, strs: Array<String>): Int {
    var steps = 0
    for (str in strs) {
        val strNo = str.trim().toInt()
        val dif = strNo - x;
        if (dif < 0) steps -= dif else steps += dif
    }
    return steps
}
