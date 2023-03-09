package model

import java.lang.Exception

class CircularMatrix(var matrix: List<List<String>> = ArrayList(ArrayList()), val c: Int, val r: Int) {

    fun move(from: Coordinate, direction: Direction, toWormhole: Coordinate? = null): Pair<Int, Coordinate> {
        val destination = getDestination(from, direction)?:toWormhole!!
        return Pair(pointsAt(destination), destination)
    }

    fun pointsAt(coordinate: Coordinate) =
        matrix[coordinate.x][coordinate.y].let { try { it.toInt() } catch (e: Exception) { 0 }
    }

    fun isWormHole(coordinate: Coordinate) = matrix[coordinate.x][coordinate.y] == "*"

    fun getDestination(coordinate: Coordinate, direction: Direction): Coordinate? {
        return when (direction) {
            Direction.U -> Coordinate(coordinate.x, if (coordinate.y == 0) r - 1 else coordinate.y - 1)
            Direction.D -> Coordinate(coordinate.x, if (coordinate.y == r - 1) 0 else coordinate.y + 1)
            Direction.L -> Coordinate(if (coordinate.x == 0) c - 1 else coordinate.x - 1, coordinate.y)
            Direction.R -> Coordinate(if (coordinate.x == c - 1) 0 else coordinate.x + 1, coordinate.y)
            Direction.W -> null
        }
    }
}