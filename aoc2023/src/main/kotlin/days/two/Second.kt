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
        set.split(",").forEach { type ->
            when (Cubes.values().first { type.contains(it.label)}){
                Cubes.RED -> maxDiceResult.red = type.filter { it.isDigit() }.toInt()
                Cubes.GREEN -> maxDiceResult.green = type.filter { it.isDigit() }.toInt()
                Cubes.BLUE -> maxDiceResult.blue = type.filter { it.isDigit() }.toInt()
            }
        }
        maxDiceResult
    }

    return diceResults.getPower()
}

class MaxDiceResult(
){
    var red : Int? = null
        set(value){
            if (field == null || value!! > field!!) field = value
        }

    var green : Int? = null
        set(value){
            if (field == null || value!! > field!!) field = value
        }

    var blue : Int? = null
        set(value){
            if (field == null || value!! > field!!) field = value
        }
}
fun List<MaxDiceResult>.getPower(): Int {
    return (this.maxBy { it.red ?: 0 }.red ?: 0) * (this.maxBy { it.green ?: 0 }.green ?: 0) * (this.maxBy { it.blue ?: 0 }.blue ?: 0)
}