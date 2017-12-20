package ex20

import FileReader
import java.util.regex.Pattern
import kotlin.math.abs

private val REGEX = Pattern.compile("p=<(\\s?-?\\d+),(-?\\d+),(-?\\d+)>,\\sv=<(\\s?-?\\d+),(-?\\d+),(-?\\d+)>,\\sa=<(\\s?-?\\d+),(-?\\d+),(-?\\d+)>").toRegex()

fun main(args: Array<String>) {
    part1()
    part2()
}

private fun part1() {
    val particles = readInput()

    for (i in 1..10_000) {
        particles.forEach { it.tick() }
    }
    println(particles.sortedBy { it.manhattanDistance() }.first().number)
}


private fun part2() {
    var particles = readInput()

    for (i in 1..10_000) {
        particles.forEach { it.tick() }
        particles = removeCollisions(particles)
    }
    println(particles.size)
}

private fun removeCollisions(input: List<Particle>): MutableList<Particle> {
    val mutable = input.toMutableList()
    val collisions = input.filter { p -> input.count { it.collidesWith(p) } > 0 }.toSet()

    mutable.removeIf { collisions.contains(it) }

    return mutable
}

private fun readInput(): MutableList<Particle> {
    var i = 0
    return FileReader.readLines("ex20/input.txt")
            .map { REGEX.matchEntire(it)!!.groupValues }
            .map { it.subList(1, it.size).map { it.trim().toLong() } }
            .map { Particle(i++, Triple(it[0], it[1], it[2]), Triple(it[3], it[4], it[5]), Triple(it[6], it[7], it[8])) }
            .toMutableList()
}

private data class Particle(val number: Int, var position: Triple, var velocity: Triple, var acceleration: Triple) {
    fun manhattanDistance() = position.sum()

    fun tick() {
        this.velocity += this.acceleration
        this.position += this.velocity
    }

    fun collidesWith(other: Particle) = this != other && this.position == other.position
}

private data class Triple(private var x: Long, private var y: Long, private var z: Long) {
    operator fun plus(other: Triple): Triple {
        return Triple(this.x + other.x, this.y + other.y, this.z + other.z)
    }

    fun sum() = abs(this.x) + abs(this.y) + abs(this.z)
}
