package days.two

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
    return this.splitResult().map { type ->
        type.filter { it.isDigit() }.toInt() <= Cubes.entries.first { type.contains(it.label) }.number
    }.all { it }
}

fun String.splitResult(): List<String> {
    return this.split(",")
}

enum class Cubes(val label: String, val number: Int) {
    RED("red", 12),
    GREEN("green", 13),
    BLUE("blue", 14)
}