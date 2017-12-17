package ex3

private const val INPUT = 312051

fun main(args: Array<String>) {
    var size = 0
    var point = Pair(0, 0)
    var direction = Direction.RIGHT
    val grid1 = HashMap<Pair<Int, Int>, Int>()
    grid1.put(point, 1)
    var latest = 1
    var counter = 1

    while (latest <= INPUT) {
        grid1.put(point, grid1.sumOfSurrounding(point))
        latest = grid1.get(point)!!

        if (counter++ == size.maxForSize()) {
            point = point.toRight()
            direction = Direction.UP
            size++
        } else {
            point = point.next(direction)
            if (point.isAtEdge(size, direction)) {
                direction = direction.next()
            }
        }
    }
    println(latest)
}


private fun HashMap<Pair<Int, Int>, Int>.sumOfSurrounding(pos: Pair<Int, Int>): Int {
    var result = 0
    for (x in pos.first - 1..pos.first + 1)
        for (y in pos.second - 1..pos.second + 1)
            result += this.getOrDefault(kotlin.Pair(x, y), 0)

    return result
}

private fun Int.maxForSize(): Int {
    return Math.pow(((this * 2) + 1).toDouble(), 2.toDouble()).toInt()
}