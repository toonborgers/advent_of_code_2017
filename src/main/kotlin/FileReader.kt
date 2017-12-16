class FileReader {
    companion object {
        fun readFile(name: String): String {
            return FileReader::class.java.getResource(name).readText()
        }

        fun readLines(name: String): List<String> {
            return readFile(name).split("\n")
        }
    }
}