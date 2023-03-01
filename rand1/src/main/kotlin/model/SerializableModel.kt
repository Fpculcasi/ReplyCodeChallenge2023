package model

import utils.InputMethod

abstract class SerializableModel() {

    abstract fun write(): String

    abstract fun read(inputMethod: InputMethod): SerializableModel

    fun readAll(n: Int, inputMethod: InputMethod): List<SerializableModel> {
        val toReturn = mutableListOf<SerializableModel>()
        repeat(n){
            toReturn.add(read(inputMethod))
        }
        return toReturn
    }
}

fun List<SerializableModel>.writeAll() = this.forEach { it.write() }