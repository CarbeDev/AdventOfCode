package days.two

import java.io.File

fun main() {
    var totalPower = 0
    File("src/main/resources/day2.txt").forEachLine { game ->
        totalPower += game.getPower()
    }

    println(totalPower)
}

fun String.getPower(): Int {
    val diceResults = this.splitIntoSets().map { set ->
        val maxDiceResult = MaxDiceResult()
        set.splitResult().forEach { type ->
            when (Cubes.entries.first { type.contains(it.label) }) {
                Cubes.RED -> maxDiceResult.red = type.filter { it.isDigit() }.toInt()
                Cubes.GREEN -> maxDiceResult.green = type.filter { it.isDigit() }.toInt()
                Cubes.BLUE -> maxDiceResult.blue = type.filter { it.isDigit() }.toInt()
            }
        }
        maxDiceResult
    }

    return diceResults.getPower()
}

class MaxDiceResult(var red: Int = 0, var green: Int = 0, var blue: Int = 0)

fun List<MaxDiceResult>.getPower(): Int {
    return this.maxOf { it.red } * this.maxOf { it.green } * this.maxOf { it.blue }
}