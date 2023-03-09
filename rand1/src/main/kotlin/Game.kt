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

    /*snakes.forEach { snake ->
        var currentDirection = Direction.D
        repeat(snake.size){
            while (!matrix.isWormHole(cursor)){
                cursor = matrix.getDestination(cursor, currentDirection)!!
            }

            if()

        }
        cursor.x += 1
    }*/

    var points = 0
    snakes.forEachIndexed { index, snake ->
        var startY = 0
        if (index < c) {
            var position = Coordinate(index, startY)
            while (matrix.isWormHole(position)) {
                position = Coordinate(index, startY)
                startY += 1
            }
            snake.start = position

            var row = position.y
            while (snake.size > 0 && row < r) {
                row += 1
                val a = matrix.move(snake.getLastPosition(), Direction.D)
                points += a.first
                position = a.second
                if (matrix.isWormHole(position)) {
                    while (matrix.isWormHole(position) && position.y < r) {
                        val b = matrix.move(position, Direction.D)
                        row += 1
                        points += b.first
                        position = b.second
                    }
                    snake.moves.add(Direction.W)
                    snake.positions.add(position)
                } else {
                    snake.moves.add(Direction.D)
                    snake.positions.add(position)
                }
                snake.size -= 1
            }
        }
    }


    println("points $points")

    //solve
    var result = ""
    snakes.forEach { result += "${it.write()}\n" }//TODO

    //print result
    outputMethod.writeSolution(result)
}