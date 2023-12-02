fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            val id = line.substringBefore(":").filter { it.isDigit() }.toInt()
            val sets = line.substringAfter(":").split(";")
            var shouldCountId = true
            sets.forEach { set ->
                set.split(",").forEach { color ->
                    when {
                        color.contains("red") -> {
                            val number = color.filter { it.isDigit() }.toInt()
                            if (number > 12) {
                                shouldCountId = false
                            }
                        }

                        color.contains("green") -> {
                            val number = color.filter { it.isDigit() }.toInt()
                            if (number > 13) {
                                shouldCountId = false
                            }
                        }

                        color.contains("blue") -> {
                            val number = color.filter { it.isDigit() }.toInt()
                            if (number > 14) {
                                shouldCountId = false
                            }
                        }
                    }
                }
            }
            if (shouldCountId) {
                result += id
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            val sets = line.substringAfter(":").split(";")
            val reds = mutableListOf<Int>()
            val greens = mutableListOf<Int>()
            val blues = mutableListOf<Int>()
            sets.forEach { set ->
                set.split(",").forEach { color ->
                    when {
                        color.contains("red") -> {
                            val number = color.filter { it.isDigit() }.toInt()
                            reds.add(number)
                        }

                        color.contains("green") -> {
                            val number = color.filter { it.isDigit() }.toInt()
                            greens.add(number)
                        }

                        color.contains("blue") -> {
                            val number = color.filter { it.isDigit() }.toInt()
                            blues.add(number)
                        }
                    }
                }
            }
            result += reds.max() * greens.max() * blues.max()
            reds.clear()
            greens.clear()
            blues.clear()
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}