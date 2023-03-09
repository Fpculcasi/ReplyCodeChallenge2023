package algs

import cache.StarPointsCacheKey
import cache.StartPointCache
import model.CircularMatrix
import model.Coordinate
import model.Direction

fun findMaxPointsStar(
    area: CircularMatrix,
    cache: StartPointCache,
    range: Int
): Int {

    var maxPointsStar = Integer.MIN_VALUE

    for (i in 0 until area.r) {
        for (j in 0 until area.c) {
            val starCenter = Coordinate(i, j)
            val cacheKey = StarPointsCacheKey(range = range, coord = starCenter)
            val starPoints = cache.get(cacheKey)
                ?: calculateStarSum(area = area, range = range, center = starCenter).also { points ->
                    cache.put(cacheKey, points)
                }
            if (starPoints > maxPointsStar) {
                maxPointsStar = starPoints
            }
        }
    }

    return maxPointsStar
}

fun calculateStarSum(
    area: CircularMatrix,
    range: Int,
    center: Coordinate
): Int {

    var currentX = center.x
    repeat(range - 1) {
        currentX = area.move(from = Coordinate(currentX, 0), direction = Direction.U).second.x
    }

    var sum = 0
    for (i in 0 until range * 2) {

        if (i <= range - 1) {

            sum += area.pointsAt(Coordinate(currentX, center.y))

            // left
            var leftYOffset = center.y
            for (j in 0 until i) {
                leftYOffset = area.move(from = Coordinate(currentX, leftYOffset), Direction.L).second.y
                sum += area.pointsAt(Coordinate(currentX, leftYOffset))
            }

            // right
            var rightYOffset = center.y
            for (j in 0 until i) {
                rightYOffset = area.move(from = Coordinate(currentX, leftYOffset), Direction.R).second.y
                sum += area.pointsAt(Coordinate(currentX, rightYOffset))
            }
        } else {
            sum += area.pointsAt(Coordinate(currentX, center.y))

            // left
            var leftYOffset = center.y
            for (j in (range - 1) until i) {
                leftYOffset = area.move(from = Coordinate(currentX, leftYOffset), Direction.L).second.y
                sum += area.pointsAt(Coordinate(currentX, leftYOffset))
            }

            // right
            var rightYOffset = center.y
            for (j in (range - 1) until i) {
                rightYOffset = area.move(from = Coordinate(currentX, leftYOffset), Direction.R).second.y
                sum += area.pointsAt(Coordinate(currentX, rightYOffset))
            }
        }

        currentX = area.move(from = Coordinate(x = currentX, y = center.y), Direction.D).second.y
    }

    return sum
}
