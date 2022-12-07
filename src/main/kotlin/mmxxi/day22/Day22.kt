package mmxxi.day22

import utils.FileHandler
import java.io.File

fun main() {
    Day22().main()
}

class Day22 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/mmxxi/day22/input-1.txt"))
    private var lights = mutableSetOf<String>()

    fun main() {

        for (line in lines) {
            val power = line.split(" ")[0]
            val ranges = line.split(" ")[1].split(",")
            val xRange = parseRange(ranges[0])
            val yRange = parseRange(ranges[1])
            val zRange = parseRange(ranges[2])
            println("$line\t\t\t$xRange\t$yRange\t$zRange")
            if (power == "on") {
                val tmpLights = mutableSetOf<String>()
                for (zid in zRange.first..zRange.second)
                    for (xid in xRange.first..xRange.second)
                        for (yid in yRange.first..yRange.second)
                            tmpLights.add("$xid,$yid,$zid")
                lights.addAll(tmpLights)
            } else {
                val tmpLights = mutableSetOf<String>()
                for (zid in zRange.first..zRange.second)
                    for (xid in xRange.first..xRange.second)
                        for (yid in yRange.first..yRange.second)
                            tmpLights.remove("$xid,$yid,$zid")
                lights.retainAll(tmpLights)
            }
        }

        println("lights...: ${lights.size}")
    }

    private fun parseRange(input: String): Pair<Int, Int> {
        val tmp = input.substring(2).split("..")
        return Pair(tmp[0].toInt(), tmp[1].toInt())
    }
}
