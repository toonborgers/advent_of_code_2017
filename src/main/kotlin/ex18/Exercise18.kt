package ex18

import FileReader
import kotlin.system.exitProcess


val registers = mapOf<String, Int>().toMutableMap()
var last = -1

fun main(args: Array<String>) {
    val input = FileReader.readLines("ex18/input.txt")
    var index = 0

    while (index >= 0 && index < input.size) {
        val operation = input[index].split(" ").map { it.trim() }
        var addToIndex = 1

        if (isArithmetic(operation)) {
            handleArithmetic(operation)
        } else if (operation[0] == "snd") {
            last = value(operation[1])
        } else if (operation[0] == "rcv") {
            if (value(operation[1]) != 0) {
                println(last)
                exitProcess(0)
            }
        } else if (operation[0] == "jgz") {
            if (value(operation[1]) > 0) {
                addToIndex = value(operation[2])
            }
        }

        index += addToIndex
    }
}

private fun handleArithmetic(line: List<String>) {
    when (line[0]) {
        "set" -> registers.put(line[1], value(line[2]))
        "add" -> registers.put(line[1], value(line[1]) + value(line[2]))
        "mul" -> registers.put(line[1], value(line[1]) * value(line[2]))
        "mod" -> registers.put(line[1], value(line[1]) % value(line[2]))
    }
}

private fun isArithmetic(line: List<String>): Boolean {
    return listOf("set", "add", "mul", "mod").contains(line[0])
}

private fun value(input: String): Int {
    return try {
        input.trim().toInt()
    } catch (e: Exception) {
        registers.getOrDefault(input.trim(), 0)
    }
}