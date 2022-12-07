package utils

import java.io.File
import java.io.InputStream
import kotlin.text.Charsets.UTF_8

class FileHandler {

    fun getLinesAsInt(file: File): MutableList<Int> {
        val inputStream: InputStream = file.inputStream()
        val lineList = mutableListOf<Int>()
        inputStream.bufferedReader().forEachLine { lineList.add(it.toInt()) }

        return lineList
    }

    fun getLinesAsString(file: File): MutableList<String> {
        val inputStream: InputStream = file.inputStream()
        val lineList = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine { lineList.add(it) }

        return lineList
    }

    fun readFileAsTextUsingInputStream(fileName: String) =
        File(fileName).inputStream().readBytes().toString(UTF_8)
}
