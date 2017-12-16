class FileReader {
    companion object {
        fun readFile(name: String): String {
            return FileReader::class.java.getResource(name).readText()
        }

        fun readFile(name: String, splitChar: String): List<String> {
            return readFile(name).split(splitChar).filter { it.isNotEmpty() }
        }

        fun <T> readFile(name: String, splitChar: String, mapper: (m: String) -> T): List<T> {
            return readFile(name, splitChar).map(mapper)
        }

        fun readLines(name: String): List<String> {
            return readFile(name).split("\n")
        }

        fun <T> readLines(name: String, mapper: (m: String) -> T): List<T> {
            return readFile(name, "\n").map(mapper)
        }
    }
}