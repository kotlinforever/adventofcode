package utils

data class Point(var rowNo: Int, var idx: Int) : Comparable<Point> {
    override fun compareTo(other: Point) = compareValuesBy(this, other,
        { it.rowNo },
        { it.idx }
    )
}
