package mmxxi.day12

import utils.FileHandler
import java.io.File

fun main() {
    Day12().main()
}

class Day12 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/mmxxi/day12/input01"))
    private var map = mutableMapOf<String, MutableList<String>>()

    fun main() {
        for (line in lines) {
            val lineSplit = line.split("-")
            if (!map.containsKey(lineSplit[0])) map[lineSplit[0]] = mutableListOf()
            map[lineSplit[0]]?.add(lineSplit[1])

            if (!map.containsKey(lineSplit[1])) map[lineSplit[1]] = mutableListOf()
            if (lineSplit[0] != "start") map[lineSplit[1]]?.add(lineSplit[0])
        }

        println(map)

        val routes = mutableSetOf<MutableList<String>>()
        for (startPoint in map["start"]!!) {
            routes.addAll(findRoute(mutableListOf("start", startPoint), mutableSetOf()))
        }

        for (route in routes) {
            for (x in route)
                print("$x,")
            println()
        }
    }

    private fun findRoute(
        aRoute: MutableList<String>,
        aRoutes: MutableSet<MutableList<String>>
    ): MutableSet<MutableList<String>> {

//        println("findRoute\t$aRoute\t$aRoutes")
        val route = aRoute.toMutableList()
        val routes = aRoutes.toMutableSet()

        val lastPoint = route[route.size - 1]

        if (map.contains(lastPoint)) {
            for (point in map[lastPoint]!!) {
                val newRoute = route.toMutableList()
                if (point == "end") {
                    newRoute.add(point)
                    routes.add(newRoute)
                    return routes
                } else if (point == point.lowercase() && newRoute.contains(point)) {
//                    routes.add(newRoute)
                    println("Found $point in $route")
                    return routes
                } else {
                    newRoute.add(point)
                    routes.addAll(findRoute(newRoute, routes))
                }
            }
        } else {
            println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
            route.add(lastPoint)
            routes.add(route)
            return routes
        }

        println("YYY")
        return routes
    }
}

//start,A,b,d,end
//start,b,d,end


//    start-A
//    start-b
//    A-c
//    A-b
//    b-d
//    A-end
//    b-end

//    start,A,b,A,c,A,end
//    start,A,b,A,end
//    start,A,b,end
//    start,A,c,A,b,A,end
//    start,A,c,A,b,end
//    start,A,c,A,end
//    start,A,end
//    start,b,A,c,A,end
//    start,b,A,end
//    start,b,end
