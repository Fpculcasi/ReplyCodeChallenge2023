package cache

import model.Coordinate

data class StarPointsCacheKey(
    val range: Int,
    val coord: Coordinate
)

class StartPointCache {

    private val cache: MutableMap<StarPointsCacheKey, Int> = mutableMapOf()

    fun put(key: StarPointsCacheKey, point: Int) {
        cache[key] = point
    }

    fun get(key: StarPointsCacheKey): Int? {
        return cache[key]
    }

    fun clear() {
        cache.clear()
    }

}