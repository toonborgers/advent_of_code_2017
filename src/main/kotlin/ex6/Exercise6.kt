package ex6


fun main(args: Array<String>) {
    val states = listOf<String>().toMutableList()
    val state = FileReader.readFile("ex6/input.txt", "\t", { it.toInt() })

    var stateStr = state.joinToString("")
    while (!states.contains(stateStr)) {
        states.add(stateStr)

        val highest = state.max()
        val index = state.indexOf(highest)
        state[index] = 0
        (1..highest!! + 1)
                .map { (index + it) % state.size }
                .forEach { state[it] = state[it] + 1 }

        stateStr = state.joinToString("")

    }

    println(states.size)
}