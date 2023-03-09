package model

import utils.InputMethod

data class SnakeSerializableModelImpl constructor(
    var size: Int,
    var start: Coordinate? = null,
    var positions: MutableList<Coordinate> = mutableListOf(),
    val moves: MutableList<Direction> = mutableListOf(),
    val points: Int = 0
) : SerializableModel() {

    override fun write(): String {
        return "${start?.x} ${start?.y} ${printPath()}"
    }

    private fun printPath(): String {
        var toReturn = ""

        repeat(moves.size) {
            if (moves[it] != Direction.W) {
                toReturn += moves[it]
            } else {
                toReturn += positions[it]
            }

            if (it < moves.size - 1) toReturn += " "
        }

        return toReturn
    }

    override fun read(inputMethod: InputMethod): SerializableModel {
        inputMethod.readMultimpleValuesLine()
        return SnakeSerializableModelImpl(0)
    }

    fun getLastPosition(): Coordinate {
        return if (positions.isEmpty()) start!! else positions.last()
    }
}