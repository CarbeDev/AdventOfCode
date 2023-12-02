package days

import java.io.File

fun main() {
    var gameId = 0
    var compteur = 0
    File("src/main/resources/day2.txt").forEachLine { game ->
        gameId++
        if (game.splitIntoSets().all { set -> set.isSetValid() }) compteur += gameId
    }

    println(compteur)
}

fun String.splitIntoSets(): List<String> {
    return this.substringAfter(":").split(";")
}

fun String.isSetValid(): Boolean {
    val split = this.split(",")
    return split.map { type ->
        type.filter { it.isDigit() }.toInt() <= Cubes.values().first { type.contains(it.label) }.number
    }.all { it }
}

enum class Cubes(val label: String, val number: Int) {
    RED("red", 12),
    GREEN("green", 13),
    BLUE("blue", 14)
}