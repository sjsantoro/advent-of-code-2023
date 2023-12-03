import kotlin.math.min

data class CubeSetup(
    var green: Int,
    var red: Int,
    var blue: Int
) {
    val power: Int
        get() = green * red * blue
}

data class GameResult(
    var green: Int,
    var red: Int,
    var blue: Int
)

data class Game(
    val id: Int,
    val results: MutableList<GameResult>,
)

fun main() {
    fun part1(games: List<Game>, cubeSetup: CubeSetup): Int {

        var possibleCount = 0

        for (game in games) {
            var possible = true
            for (result in game.results) {
                if (result.blue > cubeSetup.blue || result.red > cubeSetup.red || result.green > cubeSetup.green) {
                    possible = false
                    break
                }
            }

            if (possible) {
                possibleCount += game.id
            }
        }

        return possibleCount
    }

    fun part2(games: List<Game>): Int {

        var power = 0

        for (game in games) {
            val minimumCubeSetup = CubeSetup(0, 0, 0)

            for (result in game.results) {
                if (result.blue > minimumCubeSetup.blue) {
                    minimumCubeSetup.blue = result.blue
                }

                if (result.red > minimumCubeSetup.red) {
                    minimumCubeSetup.red = result.red
                }
                if (result.green > minimumCubeSetup.green) {
                    minimumCubeSetup.green = result.green
                }
            }

            power += minimumCubeSetup.power
        }

        return power
    }

    fun processInput(input: List<String>): List<Game> {
        val games: MutableList<Game> = mutableListOf()

        for (line in input) {
            val gameAndResults = line.split(":")

            val gameId = gameAndResults.first().split(" ").last().toInt()
            val draws = gameAndResults.last()

            val game = Game(
                    id = gameId,
                    results = mutableListOf()
            )

            val cubeSets = draws.split(";")

            for (cubeSet in cubeSets) {

                val gameResult = GameResult(
                    blue = 0,
                    green = 0,
                    red = 0
                )

                val amountAndColors = cubeSet.trim().split(",")

                for (amountAndColor in amountAndColors) {
                    val cubeData = amountAndColor.trim().split(" ")

                    val amount = cubeData.first().toInt()
                    val color = cubeData.last().trim()

                    when (color) {
                        "blue" -> gameResult.blue = amount
                        "green" -> gameResult.green = amount
                        "red" -> gameResult.red = amount
                    }
                }

                game.results.add(gameResult)
            }

            games.add(game)
        }

        return games
    }

    val testInput = readInput("Day02_test")
    val processedTestInput = processInput(testInput)
    val cubeSetup = CubeSetup(red = 12, green = 13, blue = 14)

    val output = part1(processedTestInput, cubeSetup)
    check(output == 8)

    val input = readInput("Day02")
    val processedInput = processInput(input)
    part1(processedInput, cubeSetup).println()

    val output2 = part2(processedTestInput)
    check(output2 == 2286)

    part2(processedInput).println()
}
