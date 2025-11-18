package com.cksckckcks.downloadifyoucan.model

import com.cksckckcks.downloadifyoucan.database.Todo

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

fun Todo.toToDo(): ToDo {
    val date = dueDate.split("-")

    return ToDo(
        id = id.toInt(),
        title = title,
        description = description ?: "",
        year = date.getOrNull(0)?.toIntOrNull() ?: 0,
        month = date.getOrNull(1)?.toIntOrNull() ?: 0,
        day = date.getOrNull(2)?.toIntOrNull() ?: 0,
        priority = priority.toInt().toPriority(),
        isDone = isDone
    )
}
