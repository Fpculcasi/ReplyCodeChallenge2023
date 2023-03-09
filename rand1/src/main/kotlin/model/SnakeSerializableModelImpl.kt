package model

import utils.InputMethod

data class SnakeSerializableModelImpl constructor(
    val size: Int,
    var start: Coordinate? = null,
    var positions: List<Coordinate> = ArrayList(),
    val moves: List<Direction> = ArrayList(),
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

            if (it != moves.size - 1) toReturn += " "
        }

        return toReturn
    }

    override fun read(inputMethod: InputMethod): SerializableModel {
        inputMethod.readMultimpleValuesLine()
        return SnakeSerializableModelImpl(0)
    }
}