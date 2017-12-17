package ex3

import kotlin.math.absoluteValue


fun main(args: Array<String>) {
    val grid = HashMap<Pair<Int, Int>, Int>()

    var layer = Layer(0)
    var x = 0
    var xPlus = 1
    var y = 0
    var yPlus = 0
    var value = 1

    grid.put(Pair(0, 0), 1)

    while (value <= 312051) {

        x += xPlus
        y += yPlus

        val pair = Pair(x, y)
        value = pair.occupiedNeighbourSum(grid)
        grid.put(pair, value)

        when {
            layer.atBottomRight(value) -> {
                layer = layer.next()
                xPlus = 0
                yPlus = -1
            }
            layer.atTopRight(value) -> {
                xPlus = -1
                yPlus = 0
            }
            layer.atTopLeft(value) -> {
                xPlus = 0
                yPlus = 1
            }
            layer.atBottomLeft(value) -> {
                xPlus = 1
                yPlus = 0
            }
        }
    }

    println(value)
}

private fun Pair<Int, Int>.occupiedNeighbourSum(grid: Map<Pair<Int, Int>, Int>): Int {
    if (this.first == 0 && this.second == 0) {
        return 1
    }

    return listOf(
            Pair(this.first + 1, this.second),
            Pair(this.first + 1, this.second + 1),
            Pair(this.first, this.second + 1),
            Pair(this.first - 1, this.second + 1),
            Pair(this.first - 1, this.second),
            Pair(this.first - 1, this.second - 1),
            Pair(this.first, this.second - 1),
            Pair(this.first + 1, this.second - 1)
    )
            .map { grid.getOrDefault(it, 0) }
            .sum()
}

private fun part1() {
    val grid = HashMap<Pair<Int, Int>, Int>()

    var layer = Layer(0)
    var x = 0
    var xPlus = 1
    var y = 0
    var yPlus = 0
    var value = 1

    grid.put(Pair(0, 0), 1)
    while (value < 312051) {

        x += xPlus
        y += yPlus
        grid.put(Pair(x, y), ++value)

        when {
            layer.atBottomRight(value) -> {
                layer = layer.next()
                xPlus = 0
                yPlus = -1
            }
            layer.atTopRight(value) -> {
                xPlus = -1
                yPlus = 0
            }
            layer.atTopLeft(value) -> {
                xPlus = 0
                yPlus = 1
            }
            layer.atBottomLeft(value) -> {
                xPlus = 1
                yPlus = 0
            }
        }
    }

    println(x.absoluteValue + y.absoluteValue)
}


class Layer(private val size: Int) {
    private var max = -1

    init {
        layerCache.put(size, this)
    }

    fun next(): Layer {
        return layerCache.getOrDefault(size + 1, Layer(size + 1))
    }

    fun prev(): Layer {
        return layerCache.getOrDefault(size - 1, Layer(size - 1))
    }

    fun atBottomRight(value: Int): Boolean {
        if (size == 0) {
            return true
        }
        return value == max() + 1
    }

    fun atTopRight(value: Int): Boolean {
        return value == prev().max() + (size * 2)
    }

    fun atTopLeft(value: Int): Boolean {
        return value == prev().max() + (size * 4)
    }

    fun atBottomLeft(value: Int): Boolean {
        return value == prev().max() + (size * 6)
    }

    private fun amountOfElements(): Int {
        if (size == 0) {
            return 1
        }

        val maxWidth = (size * 2) + 1

        return (maxWidth * 2) + ((maxWidth - 2) * 2)
    }

    private fun max(): Int {
        if (max > -1) {
            return max
        }

        if (size == 0) return 1

        max = this.amountOfElements() + prev().max()
        return max
    }

    companion object {
        private val layerCache = HashMap<Int, Layer>()
    }
}