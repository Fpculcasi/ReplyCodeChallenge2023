package model

import utils.InputMethod

data class ExampleSerializableModelImpl private constructor(var aaa: Int, var bbb: String) : SerializableModel() {

    override fun write(): String {
        return this.aaa.toString()
    }

    override fun read(inputMethod: InputMethod): SerializableModel {
        val args = inputMethod.readMultimpleValuesLine()
        return ExampleSerializableModelImpl(
            aaa = args[0].toInt(),
            bbb = args[1]
        )
    }

    companion object {
        fun read(inputMethod: InputMethod): SerializableModel {
            val args = inputMethod.readMultimpleValuesLine()
            return ExampleSerializableModelImpl(
                aaa = args[0].toInt(),
                bbb = args[1]
            )
        }

        fun readAll(n: Int, inputMethod: InputMethod): List<SerializableModel> {
            val toReturn = mutableListOf<SerializableModel>()
            repeat(n) {
                toReturn.add(read(inputMethod))
            }
            return toReturn
        }
    }
}