package mmxxi.day21

fun main() {
    Day21().main()
}

class Day21 {

    // [player-2, player-1]
//    private val position = mutableListOf<Int>(8, 4)
    private val position = mutableListOf<Int>(4, 10)
    private val points = mutableListOf<Int>(0, 0)

    fun main() {
        println("test")

        var totalRolls = 0
        var dices = ""
        var roll = 0
        var diceSum = 0
        loop@ while (true)
            for (dice in 1..100) {
                totalRolls++
                dices += "+$dice"
                diceSum += dice
                roll++
                if (roll == 3) {
                    val player = dice % 2
//                    println("nu..:$dice | sum..: $diceSum | player..: $player | position...: $position | points...: $points")

                    position[player] = (position[player] + (diceSum % 10)) % 10

                    var point = position[player]
                    if (point == 0) point = 10
                    points[player] = points[player] + point

                    println("Player $player rolls $dices and moves to space ${position[player]} for a total score of ${points[player]}")

                    if (points[player] >= 1000) {
                        println("Winner")
                        var losingPlayer = points[(dice + 1) % 2]
                        println("Result = $losingPlayer * $totalRolls = ${losingPlayer * totalRolls}")
                        break@loop
                    }

                    diceSum = 0
                    roll = 0
                    dices = ""
                }

//            val rounds = dice / 10
//            val steps = dice % 10
//            println("$dice : $rounds + $steps")
            }
    }

    fun isWinner(): Boolean {
        var result = false
        for (point in points) {
            if (point > 1000) result = true
        }
        return result
    }
}
