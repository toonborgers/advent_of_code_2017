package ex5

import FileReader
import kotlin.math.absoluteValue


fun main(args: Array<String>) {
    part1()

    val maze = maze()
    var index = 0
    var steps = 0

    while (index.absoluteValue < maze.size) {
        val current = maze[index]
        if (current >= 3) {
            maze[index] = current - 1
        } else {
            maze[index] = current + 1
        }
        index += current

        steps++
    }


    println(steps)
}

private fun part1() {
    val maze = maze()
    var index = 0
    var steps = 0

    while (index.absoluteValue < maze.size) {
        val current = maze[index]
        maze[index] = current + 1
        index += current

        steps++
    }

    println(steps)
}

private fun maze() = ArrayList(FileReader.readLines("ex5/input.txt", { it.toInt() }))