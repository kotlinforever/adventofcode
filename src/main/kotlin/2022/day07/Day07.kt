package `2022`.day07

import utils.FileHandler
import java.io.File

fun main() {
    Day07().main()
}

class Day07 {

    val map = mutableMapOf<String, Int>()

    fun main() {
//        part1("src/main/kotlin/2022/day07/input")
        part1("src/main/kotlin/2022/day07/input.txt")
        part2()
    }

    private fun part2() {
        val used = 70000000 - map["/"]!!
        println("used..: $used")
        val need = 30000000 - used
        println("need..: $need")

        val result = map.values.sorted()
        for (x in result) {
            if (x > need) {
                println("res....:$x")
                return
            }
        }
    }

    private fun part1(file: String) {
        val lines = FileHandler().getLinesAsString(File(file))


        var path = ""
        for (line in lines) {
            if (line.startsWith("$")) {
                if (line.startsWith("\$ cd")) {
                    if (line.startsWith("\$ cd /")) {
                        path = "/"
                    } else if (line.startsWith("\$ cd ..")) {
                        path = path.substring(0, path.lastIndexOf("/"))
                    } else {
                        if (!path.endsWith("/")) {
                            path += "/"
                        }
                        path += line.substring(5)
                    }
                }
            } else if (line.startsWith("dir")) {
                // Ignore
            } else {
                val size = line.split(" ")[0].toInt()
                println("$path    $line  ::: $size")

                if (path == "/") {
                    val d = "/"
                    addToMap(d, size)
                } else {
                    var tmpPath = path
                    while (tmpPath != "") {
                        addToMap(tmpPath, size)
                        tmpPath = tmpPath.substring(0, tmpPath.lastIndexOf("/"))
                        if (tmpPath == "") {
                            tmpPath = "/"
                            addToMap(tmpPath, size)
                            tmpPath = ""
                        }
                    }
                }
            }
        }

        println("************************************")

        var res = 0
        for (key in map.keys) {
            if (map[key]!! < 100000) {
                res += map[key]!!
            }
            println("$key         ${map[key]}")
        }

        println("res..: $res")

    }

    fun addToMap(key: String, size: Int) {
        var dirSize = 0
        if (map.containsKey(key)) {
            dirSize = map[key]!!
        }
        dirSize += size
        map[key] = dirSize
    }
}
