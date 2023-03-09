package model

import utils.InputMethod

data class SnakeSerializableModelImpl constructor(val size : Int, var c: Int = -1, var r: Int = -1, var path: List<String> = ArrayList()) : SerializableModel() {

    override fun write(): String {
        return "$c $r ${printPath()}"
    }

    private fun printPath():String {
        return path.joinToString { " " }
    }

    override fun read(inputMethod: InputMethod): SerializableModel {
        val args = inputMethod.readMultimpleValuesLine()
        return SnakeSerializableModelImpl(0)
    }
}