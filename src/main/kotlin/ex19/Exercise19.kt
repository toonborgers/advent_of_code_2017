package ex19

import FileReader
import kotlin.math.absoluteValue


fun main(args: Array<String>) {
    val (grid, x) = readInput()

    var yPlus = 1
    var xPlus = 0
    var position = Pair(x, 0)
    var text = ""

    var steps = 0
    while (grid.getOrDefault(position, "") != "") {
        steps++
        val next = grid.getOrDefault(position, "")

        position = if (next == "+") {
            if (yPlus.absoluteValue == 1) {
                yPlus = 0
                if (grid.getOrDefault(position.left(), "").isNotBlank()) {
                    xPlus = -1
                    position.left()

                } else {
                    xPlus = 1
                    position.right()
                }
            } else {
                xPlus = 0
                if (grid.getOrDefault(position.down(), "").isNotBlank()) {
                    yPlus = 1
                    position.down()

                } else {
                    yPlus = -1
                    position.up()
                }
            }
        } else if (next[0].isLetter()) {
            text += next
            position.next(xPlus, yPlus)
        } else {
            position.next(xPlus, yPlus)
        }
    }


    println(steps)
    println(text)
}


fun readInput(): Pair<MutableMap<Pair<Int, Int>, String>, Int> {
    val lines = FileReader.readLines("ex19/input.txt", "")
    val grid = mapOf<Pair<Int, Int>, String>().toMutableMap()

    var startX = 0
    for (y in 0 until lines.size) {
        val line = lines[y]
        for (x in 0 until line.size) {
            val item = line[x].trim()
            grid.put(Pair(x, y), item)
            if (y == 0 && item.isNotBlank()) {
                startX = x
            }
        }
    }

    return Pair(grid, startX)
}

fun Pair<Int, Int>.next(xPlus: Int, yPlus: Int): Pair<Int, Int> {
    return Pair(first + xPlus, second + yPlus)
}

fun Pair<Int, Int>.left(): Pair<Int, Int> {
    return this.next(-1, 0)
}

fun Pair<Int, Int>.right(): Pair<Int, Int> {
    return this.next(1, 0)
}

fun Pair<Int, Int>.up(): Pair<Int, Int> {
    return this.next(0, -1)
}

fun Pair<Int, Int>.down(): Pair<Int, Int> {
    return this.next(0, 1)
}