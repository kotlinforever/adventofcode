@file:Suppress("DuplicatedCode")

package mmxxi.day21

fun main() {
    Day21part2().main()
}

class Game {
    var position = mutableListOf(8, 4)

    //    private val position = mutableListOf(4, 10)
    var points = mutableListOf(0, 0)
    var totalRolls = 0


    fun move(diceSum: Int): Boolean {
        totalRolls++
        val player = totalRolls % 2
//            println("nu..:$dice | sum..: $diceSum | player..: $player | position...: $position | points...: $points")

        position[player] = (position[player] + (diceSum % 10)) % 10

        var point = position[player]
        if (point == 0) point = 10
        points[player] = points[player] + point

        println("Player $player rolls moves to space ${position[player]} for a total score of ${points[player]}")

        if (points[player] >= 21) {
            println("Winner")
            val losingPlayer = points[(totalRolls + 1) % 2]
            println("Result = $losingPlayer * $totalRolls = ${losingPlayer * totalRolls}")
            return true
        }
        return false
    }
}

@Suppress("DuplicatedCode", "DuplicatedCode")
class Day21part2 {

    fun main() {
        println("test")
        val games = mutableListOf(Game())
        val newGames = mutableListOf<Game>()

        while (!games.isEmpty()) {
            for (game in games) {
                for (x in 3..9) {
                    val newGame = game.clone()
                    if (game.move(x)) {
                        println("Got winner")
                    } else
                        newGames.add(newGame)
                }
            }
            games.clear()
            games.addAll(newGames)
            newGames.clear()
        }


//        loop@ while (true)
//            for (dice in 1..3) {
//
//                if (game.move(dice)) {
//                    println("Got winner")
//                    break@loop
//                }
//            }
    }

    fun Game.clone(): Game {
        val game = Game()
        game.position = position
        game.points = points
        game.totalRolls = totalRolls
        return game
    }
}
