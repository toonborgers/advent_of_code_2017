class FileReader {
    companion object {
        fun readFile(name: String): String {
            return FileReader::class.java.getResource(name).readText()
        }

        fun readFile(name: String, splitChar: String): MutableList<String> {
            return readFile(name).split(splitChar).filter { it.isNotEmpty() }.toMutableList()
        }

        fun <T> readFile(name: String, splitChar: String, mapper: (m: String) -> T): MutableList<T> {
            return readFile(name, splitChar).map(mapper).toMutableList()
        }

        fun readLines(name: String): MutableList<String> {
            return readFile(name, "\n")
        }

        fun readLines(name: String, splitChar: String): MutableList<MutableList<String>> {
            return readLines(name)
                    .map { it.split(splitChar).toMutableList() }
                    .toMutableList()
        }

        fun <T> readLines(name: String, mapper: (m: String) -> T): MutableList<T> {
            return readLines(name).map(mapper).toMutableList()
        }

        fun <T> readLines(name: String, splitChar: String, mapper: (m: String) -> T): MutableList<MutableList<T>> {
            return readLines(name,splitChar)
                    .map { it.map(mapper).toMutableList() }
                    .toMutableList()
        }
    }
}