package com.cksckckcks.downloadifyoucan.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cksckckcks.downloadifyoucan.database.ToDoDataBase
import com.cksckckcks.downloadifyoucan.model.ToDo
import com.cksckckcks.downloadifyoucan.model.toToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock

@OptIn(kotlin.time.ExperimentalTime::class)
class MainViewModel(
    private val dataBase: ToDoDataBase
) : ViewModel() {
    private val _selectedDate = MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _todos = MutableStateFlow<List<ToDo>>(emptyList())
    val todos: StateFlow<List<ToDo>> = _todos.asStateFlow()

    private val _todoCount = MutableStateFlow(_todos.value.count())
    val todoCount = _todoCount.asStateFlow()

    private val _todoDoneCount = MutableStateFlow(_todos.value.count { it.isDone })
    val todoDoneCount = _todoDoneCount.asStateFlow()



    init {
        loadTodosByDate(_selectedDate.value)
    }

    fun updateSelectedDate(date: LocalDate) {
        _selectedDate.value = date

        loadTodosByDate(date)
    }

    private fun loadAllTodos() {
        viewModelScope.launch {
            dataBase.getAllTodos().collect { dbTodos ->
                _todos.value = dbTodos.map { it.toToDo() }

                updateCounts()
            }
        }
    }

    private fun loadTodosByDate(date: LocalDate) {
        viewModelScope.launch {
            val dateString = date.toString()

            dataBase.getTodosByDate(dateString).collect { dbTodos ->
                _todos.value = dbTodos.map { it.toToDo() }

                updateCounts()
            }
        }
    }

    fun todoDelete(id: Int) {
        viewModelScope.launch {
            dataBase.deleteTodo(id.toLong())

            updateCounts()
        }
    }

    fun todoDoneToggle(id: Int, isDone: Boolean) {
        viewModelScope.launch {
            dataBase.toggleComplete(id.toLong(), isDone = isDone)
        }
    }

    private fun updateCounts() {
        _todoCount.value = _todos.value.count()
        _todoDoneCount.value = _todos.value.count { it.isDone }
    }
}
