package ex3

private const val INPUT = 312051

fun main(args: Array<String>) {
    var position = Pair(1, 0)
    var size = 1
    var direction = Direction.UP

    for (i in 1..INPUT) {
        position = position.next(direction)
        if (position.isAtEdge(size, direction)) {
            if (direction == Direction.RIGHT) {
                size++
            }
            direction = direction.next()
        }
    }

    println(position.manhattanDistance())
}

