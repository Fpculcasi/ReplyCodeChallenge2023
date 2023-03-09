package algs

import cache.StarPointsCacheKey
import cache.StartPointCache
import model.CircularMatrix
import model.Coordinate

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

    var sum = 0

    val xLowerBound = center.x - (range - 1)
    val xUpperBound = center.x + (range - 1)

    val yLowerBound = center.y - (range - 1)
    val yUpperBound = center.y + (range - 1)

    for (i in xLowerBound..xUpperBound) {
        for (j in yLowerBound..yUpperBound) {
            sum += area.pointsAt(Coordinate(i, j))
        }
    }

    return sum

}
