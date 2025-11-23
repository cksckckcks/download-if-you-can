package com.cksckckcks.downloadifyoucan.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cksckckcks.downloadifyoucan.database.ToDoDataBase
import com.cksckckcks.downloadifyoucan.model.Priority
import com.cksckckcks.downloadifyoucan.model.toInt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock

@OptIn(kotlin.time.ExperimentalTime::class)
class AddToDoViewModel(
    private val dataBase: ToDoDataBase
) : ViewModel() {
    private val _selectedDate = MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _priority = MutableStateFlow(Priority.MEDIUM)
    val priority = _priority.asStateFlow()



    fun updateSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun updateTitle(title: String) {
        _title.value = title
    }

    fun updateDescription(description: String) {
        _description.value = description
    }

    fun updatePriority(priority: Priority) {
        _priority.value = priority
    }

    fun checkInput(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }

    fun addTodo() {
        viewModelScope.launch {
            dataBase.insertTodo(
                title = title.value,
                description = description.value,
                dueDate = selectedDate.value.toString(),
                priority = priority.value.toInt().toLong()
            )
        }
    }
}