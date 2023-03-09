package model

data class Coordinate (var x: Int, val y:Int) {
    override fun toString() = "$x $y"
}