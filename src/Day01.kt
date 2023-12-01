fun main() {
    fun part1(input: List<String>): Int = input.sumOf { line ->
        (line.first { it.isDigit() }.toString() + line.last { it.isDigit() }).toInt()
    }

    fun part2(input: List<String>): Int = input.sumOf { line ->

        var newLine = line
        val firstDigitWritten = newLine.findAnyOf(digits.keys)
        val lastDigitWritten = newLine.findLastAnyOf(digits.keys)

        firstDigitWritten?.let {
            newLine = newLine.replaceRange(it.first, it.first + it.second.length, digits[it.second]!!)
        }

        lastDigitWritten?.let {
            if (firstDigitWritten?.first != lastDigitWritten.first) {

                // Take into account potential characters shared between first and last written digit to compute the index at which to replace the last digit written
                var sizeToRemove = (firstDigitWritten!!.second.length - 1)
                sizeToRemove -= (firstDigitWritten.first + firstDigitWritten.second.length - lastDigitWritten.first).coerceAtLeast(
                    0
                )

                val index = it.first - sizeToRemove
                newLine = newLine.replaceRange(index, index + it.second.length, digits[it.second]!!)
            }
        }

        (newLine.first { it.isDigit() }.toString() + newLine.last { it.isDigit() }).toInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

val digits = mapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9"
)