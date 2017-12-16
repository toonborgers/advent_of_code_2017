package ex1


fun main(args: Array<String>) {
    part1()
    part2()
}

fun part1() {
    val input = readInput()
    println(input.filterIndexed { index, num -> num == input[(index + 1) % input.size] }
            .reduce { acc, i -> acc + i })
}

fun part2() {
    val input = readInput()
    val toAdd = input.size / 2
    println(input.filterIndexed { index, num -> num == input[(index + toAdd) % input.size] }
            .reduce { acc, i -> acc + i })
}

private fun readInput(): List<Int> {
    return FileReader.readFile("ex1/input.txt", "", { it.toInt() })
}