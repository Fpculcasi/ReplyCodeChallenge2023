import model.ExampleSerializableModelImpl
import utils.FileInputMethod
import utils.FileOutputMethod

fun solveFor(inputFilePath: String){

    val inputMethod = FileInputMethod(inputFilePath)
    val outputMethod = FileOutputMethod("solution_$inputFilePath")//StandardOutputMethod()

    //read input parameters
    val n = inputMethod.readSingleValueLine().toInt()
    //create model data
    val data = ExampleSerializableModelImpl.readAll(n, inputMethod)

    //solve
    val result : String = data.joinToString { it.write() }//TODO

    //print result
    outputMethod.writeSolution(result)
}