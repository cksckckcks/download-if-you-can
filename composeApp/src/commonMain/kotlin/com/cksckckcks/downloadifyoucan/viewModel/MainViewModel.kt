package com.cksckckcks.downloadifyoucan.viewModel

import androidx.lifecycle.ViewModel
import com.cksckckcks.downloadifyoucan.database.ToDoDataBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
}