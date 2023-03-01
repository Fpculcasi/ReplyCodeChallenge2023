package utils

fun List<String>.toInt() = this.map { it.toInt() }.toTypedArray()