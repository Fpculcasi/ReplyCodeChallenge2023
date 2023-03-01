package utils

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

interface InputMethod {
    fun readSingleValueLine(): String
    fun readMultimpleValuesLine(delimiter: String = " "): List<String>
}

class StandardInputMethod : InputMethod {
    override fun readSingleValueLine() = readLine()!!.trim()//.toInt()

    override fun readMultimpleValuesLine(delimiter: String) = readLine()!!.trimEnd().split(delimiter)
}

class FileInputMethod(filePath: String) : InputMethod {
    private val lines = ArrayList<String>()
    private var nextLine = 0

    init {
        lines.addAll(Files.readAllLines(Paths.get("src\\main\\input\\$filePath"), StandardCharsets.US_ASCII))
    }

    override fun readSingleValueLine(): String {
        return lines[nextLine++]
    }

    override fun readMultimpleValuesLine(delimiter: String): List<String> {
        return lines[nextLine++].split(delimiter)
    }
}




interface OutputMethod {
    fun writeSolution(string: String)
}

class StandardOutputMethod : OutputMethod {
    override fun writeSolution(string: String) {
        print(string)
    }
}

class FileOutputMethod(private val filePath: String) : OutputMethod {
    override fun writeSolution(string: String) {
        Files.write(Paths.get("src\\main\\output\\$filePath"), string.toByteArray(StandardCharsets.US_ASCII))
    }
}