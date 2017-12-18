package ex18

import FileReader
import com.google.common.base.Stopwatch
import java.math.BigInteger
import java.util.*


fun main(args: Array<String>) {
    val input = FileReader.readLines("ex18/input.txt", " ")

    val program0 = Program(BigInteger.ZERO, input)
    val program1 = Program(BigInteger.ONE, input)

    program0.other = program1
    program1.other = program0

    val stopwatch = Stopwatch.createStarted()
    while (!((program0.allInstructionsProcessed() && program1.allInstructionsProcessed()) || (program0.inDeadlock() && program1.inDeadlock()))) {
        while (!program0.allInstructionsProcessed() && !program0.inDeadlock()) {
            program0.run()
        }

        while (!program1.allInstructionsProcessed() && !program1.inDeadlock()) {
            program1.run()
        }
    }

    println(stopwatch)
    println(program1.sentMessages)
}


class Program(id: BigInteger, private val instructions: List<List<String>>) {
    private val registers = mapOf<String, BigInteger>().toMutableMap()
    private val received:Queue<BigInteger> = ArrayDeque<BigInteger>()
    private var index = 0
    var other: Program? = null
    var sentMessages = 0

    init {
        registers.put("p", id)
    }


    fun receive(value: BigInteger) {
        received.add(value)
    }

    fun sendToOther(value: BigInteger) {
        sentMessages++
        other?.receive(value)
    }

    fun run() {
        val operation = instructions[index]
        var addToIndex = 1

        if (isArithmetic(operation)) {
            handleArithmetic(operation)
        } else if (operation[0] == "snd") {
            sendToOther(value(operation[1]))
        } else if (operation[0] == "rcv") {
            registers.put(operation[1], received.poll())
        } else if (operation[0] == "jgz") {
            if (value(operation[1]) > BigInteger.ZERO) {
                addToIndex = value(operation[2]).toInt()
            }
        }

        index += addToIndex
    }

    fun allInstructionsProcessed(): Boolean = index < 0 || index >= instructions.size
    fun inDeadlock(): Boolean = received.isEmpty() && instructions[index][0] == "rcv"

    private fun handleArithmetic(line: List<String>) {
        when (line[0]) {
            "set" -> registers.put(line[1], value(line[2]))
            "add" -> registers.put(line[1], value(line[1]) + value(line[2]))
            "mul" -> registers.put(line[1], value(line[1]) * value(line[2]))
            "mod" -> registers.put(line[1], value(line[1]) % value(line[2]))
        }
    }

    private fun isArithmetic(line: List<String>): Boolean {
        return listOf("set", "add", "mul", "mod").contains(line[0])
    }

    private fun value(input: String): BigInteger {
        return try {
            BigInteger.valueOf(input.trim().toLong())
        } catch (e: Exception) {
            registers.getOrDefault(input.trim(), BigInteger.ZERO)
        }
    }
}