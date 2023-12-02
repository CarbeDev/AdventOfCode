package days

import java.io.File

//2512
fun main() {
    var gameId = 0
    var compteur = 0
    File("src/main/resources/day2.txt").forEachLine { line ->
        gameId++
        if (line.parseLine().getSets().all { it.isSetValid() }) compteur+=gameId
        println("$line  ${line.parseLine().getSets().all { it.isSetValid() }}")
    }

    println(compteur)
}

fun String.parseLine() : String{
        return this.substringAfter(":")
}

fun String.getSets() : List<String>{
    return this.split(";")
}

fun String.isSetValid() : Boolean {
    val split = this.split(",")
    return split.map {type ->
            val toInt = type.filter { it.isDigit() }.toInt()
        val number = Cubes.values().first { type.contains(it.label) }.number
        val b = toInt <= number
        b
        }.all { it == true }
}

enum class Cubes(val label: String,val number : Int){
    RED("red",12),
    GREEN("green",13),
    BLUE("blue", 14)
}