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
            return readFile(name, "\n")
        }

        fun readLines(name: String, splitChar: String): List<List<String>> {
            return readLines(name)
                    .map { it.split(splitChar) }
        }

        fun <T> readLines(name: String, mapper: (m: String) -> T): List<T> {
            return readLines(name).map(mapper)
        }

        fun <T> readLines(name: String, splitChar: String, mapper: (m: String) -> T): List<List<T>> {
            return readLines(name)
                    .map { it.split(splitChar).map(mapper) }
        }
    }
}