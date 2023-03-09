import model.SnakeSerializableModelImpl
import utils.FileInputMethod
import utils.FileOutputMethod
import utils.toInt

fun solveFor(inputFilePath: String){

    val inputMethod = FileInputMethod(inputFilePath)
    val outputMethod = FileOutputMethod("solution_$inputFilePath")//StandardOutputMethod()

    //read input parameters
    val params = inputMethod.readMultimpleValuesLine().toInt()
    val c = params[0]
    val r = params[1]
    val s = params[2]

    //create model data
    val snakes = inputMethod.readMultimpleValuesLine().toInt().map { SnakeSerializableModelImpl(it) }

    val v = mutableListOf<List<String>>()
    repeat(r) {
        v.add(it, inputMethod.readMultimpleValuesLine())
    }

    println(snakes)

    //solve
    val result : String = snakes.joinToString { it.write() }//TODO

    //print result
    outputMethod.writeSolution(result)
}