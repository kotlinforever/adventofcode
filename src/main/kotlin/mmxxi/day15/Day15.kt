package mmxxi.day15

import utils.FileHandler
import utils.Point
import java.io.File
import java.util.*

fun main() {
    Day15().main()
}

class Day15 {
    private var lines = FileHandler().getLinesAsString(File("src/main/kotlin/mmxxi/day15/input.txt"))
    private var table = LinkedList<MutableList<Int>>()

    fun main() {
        setupTable()

        val routes = calRoutes(mutableListOf(Point(0, 0)), mutableSetOf())
        println("Got ${routes.size} routes")

        var lowRoute = Int.MAX_VALUE
        for (route in routes) {
            val sum = getRowPoints(route)
            if (sum < lowRoute) lowRoute = sum
        }
        println(lowRoute)
//        printTable()
    }

    private fun getRowPoints(route: MutableList<Point>): Int {
        var sum = 0
        for (point in route)
            sum += table[point.rowNo][point.idx]
        return sum
    }

    private fun calRoutes(
        route: MutableList<Point>,
        routes: MutableSet<MutableList<Point>>
    ): MutableSet<MutableList<Point>> {
        val lastPoint = route[route.size - 1]

        if (lastPoint.rowNo == table.size - 1 && lastPoint.idx == table[0].size - 1) {
            // at end
            routes.add(route)
            if (routes.size % 100 == 0)
                println("Calculated ${routes.size} routes")
            return routes
        }

        if (lastPoint.idx < table[0].size - 1) {
            // Go down
            val newRoute = route.toMutableList()
            newRoute.add(Point(lastPoint.rowNo, lastPoint.idx + 1))
            routes.addAll(calRoutes(newRoute, routes))
        }

        if (lastPoint.rowNo < table.size - 1) {
            // go right
            val newRoute = route.toMutableList()
            newRoute.add(Point(lastPoint.rowNo + 1, lastPoint.idx))
            routes.addAll(calRoutes(newRoute, routes))
        }

        return routes
    }

    private fun printTable() {
        for (rowNo in 0 until table.size)
            println(table[rowNo])
    }

    private fun setupTable() {
        for (line in lines) {
            val row = arrayListOf<Int>()
            for (c in line) {
                row.add(Character.getNumericValue(c))
            }
            table.add(row.toMutableList())
        }
    }
}
