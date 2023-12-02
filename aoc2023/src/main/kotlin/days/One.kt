package days

import java.io.File

val conversionList = listOf(
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

fun main() {
    var result = 0

    File("src/main/resources/day1.txt").forEachLine { result += it.combineDigit() }
    println(result)
}

fun String.combineDigit(): Int {
    try {
        return (findFirstOccurence() + findLastOccurence()).toInt()
    } catch (e: Exception) {
        return 0
    }
}

fun String.findFirstOccurence(): String {
    val r = mutableListOf<Triple<String, String, Int>>()
    for (pair in conversionList) {
        if (this.contains(pair.first)) r.add(Triple(pair.first, pair.second, this.indexOf(pair.first)))
    }

    val substring = if (r.isNotEmpty()) this.substring(0, r.minOf { it.third }) else this
    return if (substring.getNumber().isEmpty()) r.minBy { it.third }.second else "" + this.getNumber().first()
}

fun String.findLastOccurence(): String {
    val r = mutableListOf<Triple<String, String, Int>>()
    for (pair in conversionList) {
        if (this.contains(pair.first)) r.add(Triple(pair.first, pair.second, this.lastIndexOf(pair.first)))
    }

    val substring = if (r.isNotEmpty()) this.substring(r.maxOf { it.third }, this.length) else this
    return if (substring.getNumber().isEmpty()) r.maxBy { it.third }.second else "" + this.getNumber().last()
}

fun String.getNumber(): String {
    return this.filter { it.isDigit() }
}

//Result : 54418