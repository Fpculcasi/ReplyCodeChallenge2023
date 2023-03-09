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
    var position = Coordinate(0,0)
    snakes.forEach { snake ->
        while(matrix.isWormHole(position)){
            //trova la prima posizione non wormhole
            position = matrix.getDestination(position,matrix.nextMove(position))!!
        }

        snake.start = position
        var moves = 1
        while (moves<snake.size){
            // qual è la possima mossa contigua alla posizione corrente?
            val nextMove = matrix.nextMove(position)

            // qual è la prossima posizione seguendo la prossima mossa?
            //position = matrix.getDestination(position,nextMove)!!

            // effettua prossima mossa e registrala nello snake
            val a = matrix.move(position, nextMove)
            snake.moves.add(nextMove)
            snake.positions.add(a.second)
            position=a.second
            //aggiungi punti
            points += a.first

            if(matrix.isWormHole(position)){
                //se sono caduto in un warmhole passa oltre e registra il punteggio
                snake.moves.add(Direction.W)
                val b = matrix.move(position, nextMove)
                snake.positions.add(b.second)
                position=a.second
                points += b.first
            }

            moves +=1
        }

        position = matrix.getDestination(position, matrix.nextMove(position))!!
    }

    //println(snakes)


    println("points $points")

    //solve
    var result = ""
    snakes.forEach { result += "${it.write()}\n" }//TODO

    //print result
    outputMethod.writeSolution(result)
}