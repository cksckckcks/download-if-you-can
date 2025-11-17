package com.cksckckcks.downloadifyoucan.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.datetime.*

class ToDoDataBase(driverFactory: DriverFactory) {
    private val database = DownloadIfYouCanDatabase(driverFactory.createDriver())
    private val queries = database.toDoQueries

    fun getAllTodos(): Flow<List<Todo>> {
        return queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.Default)
    }

    // 날짜로 찾기
    fun getTodosByDate(date: String): Flow<List<Todo>> {
        return queries.selectByDate(date)
            .asFlow()
            .mapToList(Dispatchers.Default)
    }

    // 오늘 날짜로 찾기
    fun getTodayTodos(): Flow<List<Todo>> {
        val today = getCurrentDate()
        return getTodosByDate(today)
    }

    // ID로 찾기
    fun getTodoById(id: Long): Flow<Todo?> {
        return queries.selectById(id)
            .asFlow()
            .mapToOneOrNull(Dispatchers.Default)
    }


    // DB 삽입
    suspend fun insertTodo(
        title: String,
        description: String? = null,
        dueDate: String,
        priority: Long = 0
    ) {
        withContext(Dispatchers.Default) {
            queries.insert(
                title = title,
                description = description,
                createdAt = getCurrentDateTime(),
                dueDate = dueDate,
                priority = priority
            )
        }
    }

    // DB 업데이트
    suspend fun updateTodo(
        id: Long,
        title: String,
        description: String?,
        dueDate: String,  // ⭐ "2025-11-16"
        priority: Long
    ) {
        withContext(Dispatchers.Default) {
            queries.update(
                title = title,
                description = description,
                dueDate = dueDate,
                priority = priority,
                id = id
            )
        }
    }

    // 완료 토글
    suspend fun toggleComplete(id: Long, isCompleted: Boolean) {
        withContext(Dispatchers.Default) {
            queries.toggleComplete(
                isCompleted = isCompleted,
                completedAt = if (isCompleted) getCurrentDateTime() else null,
                id = id
            )
        }
    }

    // 삭제
    suspend fun deleteTodo(id: Long) {
        withContext(Dispatchers.Default) {
            queries.deleteById(id)
        }
    }

    suspend fun clearAll() {
        withContext(Dispatchers.Default) {
            queries.deleteAll()
        }
    }

    private fun getCurrentDate(): String {
        return Clock.System.todayIn(TimeZone.currentSystemDefault()).toString()
    }

    private fun getCurrentDateTime(): String {
        val instant = Clock.System.now()
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        return "${localDateTime.date} ${localDateTime.time.hour.toString().padStart(2, '0')}:${localDateTime.time.minute.toString().padStart(2, '0')}:${localDateTime.time.second.toString().padStart(2, '0')}"
    }
}



fun String.toLocalDateTime(): LocalDateTime? {
    return try {
        val parts = this.split(" ")

        if (parts.size == 2) {
            val date = LocalDate.parse(parts[0])
            val time = LocalTime.parse(parts[1])

            LocalDateTime(date, time)
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}
