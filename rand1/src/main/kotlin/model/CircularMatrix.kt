package model

class CircularMatrix (var matrix: List<List<String>>? = null, val c: Int, val r: Int){

    fun move(val from: Coordinate, val direction: Direction): Pair<Int, Coordinate> {
        when(direction){
            Direction.U -> if ()
            Direction.D -> TODO()
            Direction.L -> TODO()
            Direction.R -> TODO()
            Direction.W -> TODO()
        }
        //todo
    }

    fun pointsAt(coordinate: Coordinate) = matrix[c.x][c.y]
}