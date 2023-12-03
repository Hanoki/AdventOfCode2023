fun main() {
    fun Char.isSymbol() = !isDigit() && this != '.'
    fun isSymbolAfter(line: String, endIndex: Int) = line.length > endIndex + 1 && line[endIndex + 1].isSymbol()
    fun isSymbolBefore(line: String, startIndex: Int) = startIndex != 0 && line[startIndex - 1].isSymbol()
    fun isSymbolBelow(input: List<String>, index: Int, startIndex: Int, endIndex: Int) =
        input.size > index + 1 && input[index + 1].substring(
            (startIndex - 1).coerceAtLeast(0),
            (endIndex + 1).coerceAtMost(input[index + 1].lastIndex)
        ).any { it.isSymbol() }
    fun isSymbolAbove(input: List<String>, index: Int, startIndex: Int, endIndex: Int) =
        index > 0 && input[index - 1].substring(
            (startIndex - 1).coerceAtLeast(0),
            (endIndex + 1).coerceAtMost(input[index - 1].lastIndex)
        ).any { it.isSymbol() }

    fun part1(input: List<String>): Int {
        var result = 0
        input.forEachIndexed { index, line ->
            var cutLine = line
            var indexShift = 0
            while (cutLine.any{it.isDigit()}) {
                var number = ""
                val startIndex = cutLine.indexOfFirst { it.isDigit() }
                var endIndex = startIndex
                if (endIndex != -1) {
                    do {
                        number += cutLine[endIndex]
                        endIndex++
                    } while (endIndex <= cutLine.lastIndex && cutLine[endIndex].isDigit())
                    if (
                        isSymbolAfter(cutLine, endIndex - 1) ||
                        isSymbolBefore(cutLine, startIndex) ||
                        isSymbolBelow(input, index, startIndex + indexShift, endIndex + indexShift) ||
                        isSymbolAbove(input, index, startIndex + indexShift, endIndex + indexShift)
                    ) {
                        result += number.toInt()
                    }
                }
                cutLine = cutLine.substring(endIndex)
                indexShift += endIndex
            }
        }
        return result
    }

    fun part2(): Int {
        return 0
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    part1(testInput).println()
    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part1(input).println()
    part2().println()
}