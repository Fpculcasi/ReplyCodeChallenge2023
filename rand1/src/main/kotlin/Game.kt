import model.CircularMatrix
import model.Coordinate
import model.Direction
import model.SnakeSerializableModelImpl
import utils.FileInputMethod
import utils.FileOutputMethod
import utils.toInt

fun solveFor(inputFilePath: String) {

    val inputMethod = FileInputMethod(inputFilePath)
    val outputMethod = FileOutputMethod("solution_$inputFilePath")//StandardOutputMethod()

    //read input parameters
    val params = inputMethod.readMultimpleValuesLine().toInt()
    val c = params[0]
    val r = params[1]
    val s = params[2]

    //create model data
    val snakes = inputMethod.readMultimpleValuesLine().toInt().map { SnakeSerializableModelImpl(it) }

    val wormHoles = mutableListOf<Coordinate>()
    val v = mutableListOf<List<String>>()
    repeat(r) { x ->
        val row = inputMethod.readMultimpleValuesLine()
        v.add(x, row)
        row.forEachIndexed { y, s -> if (s == "*") wormHoles.add(Coordinate(x, y)) }
    }
    val matrix = CircularMatrix(v, c, r)

    //solve
    val result: String = snakes.joinToString { it.write() }//TODO

    //print result
    outputMethod.writeSolution(result)
}