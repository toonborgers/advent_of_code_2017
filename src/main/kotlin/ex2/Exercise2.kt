package ex2

import FileReader


fun main(args: Array<String>) {
    part2()
}


fun part2() {
    doInternal { list ->
                var res = 0
                for (a in list) {
                    list
                            .filter { a > it && a % it == 0 }
                            .forEach { res = a / it }
                }

                res
            }
}

fun part1() {
    doInternal { it.last() - it.first() }
}

private fun doInternal(mapper: (m: List<Int>) -> Int) {
    println(readInput().map(mapper).reduce { acc, i -> acc + i })
}

private fun readInput() = FileReader.readLines("ex2/input.txt")
        .map { it.split("\t").map { it.toInt() }.sorted() }