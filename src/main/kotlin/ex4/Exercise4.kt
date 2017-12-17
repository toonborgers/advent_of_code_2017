package ex4


fun main(args: Array<String>) {
    part1()
    part2()
}

private fun part2() {
    println(FileReader.readLines("ex4/input.txt", " ")
            .map { it.map { it.split("").sorted().joinToString("") } }
            .filter { it.distinct().size == it.size }
            .size)
}

private fun part1() {
    println(FileReader.readLines("ex4/input.txt", " ")
            .filter { it.distinct().size == it.size }
            .size)
}