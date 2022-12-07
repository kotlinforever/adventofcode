package mmxxi.day14

import utils.FileHandler
import java.io.File

fun main() {
    Day14().main()
}

class Day14 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/mmxxi/day14/input.txt"))
    private var map = mutableMapOf<String, String>()
    private var template = ""

    fun main() {
        loadFile()
        val no = 17
        template = FileHandler().readFileAsTextUsingInputStream("src/main/kotlin/mmxxi/day14/out-${no - 1}.txt")

        val start = System.currentTimeMillis()
//        println(template)
        for (x in no..40) {
            template = calculate(template)
            File("src/main/kotlin/mmxxi/day14/out-${x}.txt").writeText(template)
            println("$x in ${(System.currentTimeMillis() - start) / 1000} sec.")
        }

        val occ = arrayListOf<Int>()
        for (c in template.toCharArray().distinct()) {
            occ.add(template.count { it == c })
        }

        occ.sort()
        println(occ)
        println(occ[occ.size - 1] - occ[0])
    }

    private fun calculate(template: String): String {
        var newString = ""
        var tmpString = ""
        for (c in template.toCharArray()) {
            tmpString += c
            if (tmpString.length == 2) {
                newString += tmpString[0] + map[tmpString]!!
                tmpString = tmpString.substring(1)
            }
        }
        newString += tmpString

//        println(newString)
        return newString
    }

    private fun loadFile() {
        for (line in lines) {
            val split = line.split(" ")
            if (split.size > 1) map[split[0]] = split[2]
            else if (line.isNotEmpty()) template = line
        }

        println("template...: $template")
        println("map........: $map")
    }
}
