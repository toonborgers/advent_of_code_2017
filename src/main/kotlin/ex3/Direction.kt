package ex3

import kotlin.math.absoluteValue

enum class Direction(val xPlus: Int, val yPlus: Int) {
    RIGHT(1, 0), UP(0, -1), LEFT(-1, 0), DOWN(0, 1);

    fun isHorizontal(): Boolean = this.ordinal % 2 == 0
    fun isVertical(): Boolean = !isHorizontal()
    fun next(): Direction = Direction.values()[(this.ordinal + 1) % Direction.values().size]
}

fun Pair<Int, Int>.next(direction: Direction): Pair<Int, Int> {
    return Pair(this.first + direction.xPlus, this.second + direction.yPlus)
}

fun Pair<Int, Int>.toRight(): Pair<Int, Int> {
    return Pair(this.first + 1, this.second)
}

fun Pair<Int, Int>.isAtEdge(size: Int, direction: Direction): Boolean {
    return direction.isHorizontal() && this.first.absoluteValue == size || direction.isVertical() && this.second.absoluteValue == size
}

fun Pair<Int, Int>.manhattanDistance(): Int = this.first.absoluteValue + this.second.absoluteValue