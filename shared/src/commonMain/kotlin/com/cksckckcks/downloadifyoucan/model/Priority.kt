package com.cksckckcks.downloadifyoucan.model

enum class Priority(val value: Int) {
    LOW(0),
    MEDIUM(1),
    HIGH(2)
}

fun Priority.toInt(): Int = value

fun Int.toPriority(): Priority = when (this) {
    0 -> Priority.LOW
    1 -> Priority.MEDIUM
    2 -> Priority.HIGH
    else -> Priority.LOW
}