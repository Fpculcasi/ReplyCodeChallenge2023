package model

import java.lang.Exception

class CircularMatrix(var matrix: List<List<String>> = ArrayList(ArrayList()), val c: Int, val r: Int) {

    fun move(from: Coordinate, direction: Direction, toWormhole: Coordinate? = null): Pair<Int, Coordinate> {
        val destination = when (direction) {
            Direction.U -> Coordinate(from.x, if (from.y == 0) r - 1 else from.y - 1)
            Direction.D -> Coordinate(from.x, if (from.y == r - 1) 0 else from.y + 1)
            Direction.L -> Coordinate(if (from.x == 0) c - 1 else from.x - 1, from.y)
            Direction.R -> Coordinate(if (from.x == c - 1) 0 else from.x + 1, from.y)
            Direction.W -> toWormhole!!
        }
        return Pair(pointsAt(destination), destination)
    }

    fun pointsAt(coordinate: Coordinate) =
        matrix[coordinate.x][coordinate.y].let { try { it.toInt() } catch (e: Exception) { 0 }
    }
}