package com.cksckckcks.downloadifyoucan.model

data class ToDo(
    val id: Int,
    val title: String,
    val description: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val priority: Priority,
    val isDone: Boolean
)
