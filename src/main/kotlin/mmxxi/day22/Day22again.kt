@file:Suppress("DuplicatedCode")

package mmxxi.day22

import utils.FileHandler
import java.io.File

fun main() {
    Day22again().main()
}

class Day22again {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/mmxxi/day22/input-1.txt"))
    private var lights = mutableSetOf<Cube>()

    fun main() {

        for (line in lines) {
            val power = line.split(" ")[0]
            val ranges = line.split(" ")[1].split(",")
            val xRange = parseRange(ranges[0])
            val yRange = parseRange(ranges[1])
            val zRange = parseRange(ranges[2])
            println("$line\t\t\t$xRange\t$yRange\t$zRange")
            if (power == "on") {
                lights.add(Cube(xRange, yRange, zRange))
            } else {
                for (light in lights)
                    if (light.turnOff(xRange, yRange, zRange)) lights.remove(light)
            }
        }

        var count = 0
        for (light in lights)
            for (idx in light.x.first..light.x.second)
                for (idy in light.y.first..light.y.second)
                    for (idz in light.z.first..light.z.second)
                        count++

        println("count...: $count")
    }

    private fun parseRange(input: String): Pair<Int, Int> {
        val tmp = input.substring(2).split("..")
        return Pair(tmp[0].toInt(), tmp[1].toInt())
    }
}

class Cube(var x: Pair<Int, Int>, var y: Pair<Int, Int>, var z: Pair<Int, Int>) {

    fun turnOff(x: Pair<Int, Int>, y: Pair<Int, Int>, z: Pair<Int, Int>): Boolean {
        if (x.first > this.x.first) this.x = Pair(x.first, this.x.second)
        if (x.second < this.x.second) this.x = Pair(this.x.first, x.second)
        if (y.first > this.y.first) this.x = Pair(y.first, this.y.second)
        if (y.second < this.y.second) this.x = Pair(this.y.first, y.second)
        if (z.first > this.z.first) this.x = Pair(z.first, this.z.second)
        if (z.second < this.z.second) this.x = Pair(this.z.first, z.second)

        return !(this.x.first >= this.x.second || this.y.first >= this.y.second || this.z.first >= this.z.second)
    }
}
