private val numbers = listOf(
        "zero",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine"
)

private fun String.textToNumber(): String =
        this.replace("one", "one1one")
                .replace("two", "two2two")
                .replace("three", "three3three")
                .replace("four", "four4four")
                .replace("five", "five5five")
                .replace("six", "six6six")
                .replace("seven", "seven7seven")
                .replace("eight", "eight8eight")
                .replace("nine", "nine9nine")

private fun String.addNumbers(): Int =
        this.first { it.isDigit() }.digitToInt() * 10 + this.last { it.isDigit() }.digitToInt()

fun main() {
    fun part1(input: List<String>): Int = input.fold(0) { acc, s -> acc + s.addNumbers() }

    fun part2(input: List<String>): Int = input.fold(0) { acc, s -> acc + s.textToNumber().addNumbers() }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val output = part1(testInput)
    check(output == 142)

    val input = readInput("Day01")
    part1(input).println()

    val testInput2 = readInput("Day01_test2")
    val output2 = part2(testInput2)
    check(output2 == 281)

    part2(input).println()
}
