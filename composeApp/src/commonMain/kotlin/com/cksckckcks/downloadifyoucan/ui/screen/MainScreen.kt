package com.cksckckcks.downloadifyoucan.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cksckckcks.downloadifyoucan.theme.MainColor
import com.cksckckcks.downloadifyoucan.theme.pretendard
import com.cksckckcks.downloadifyoucan.ui.component.ToDoCard
import com.cksckckcks.downloadifyoucan.viewModel.MainViewModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class MainScreen : Screen {
    @OptIn(ExperimentalTime::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MainViewModel = koinInject()  // Koin으로 주입!

        MainScreenContent(
            viewModel = viewModel,
            onAddClick = { navigator.push(AddToDoScreen()) }
        )
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun MainScreenContent(
    viewModel: MainViewModel,
    onAddClick: () -> Unit
) {
    val localDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
    val month = localDateTime.monthNumber
    val day = localDateTime.dayOfMonth

    val selectedDate by viewModel.selectedDate.collectAsState()
    val selectedMonth = selectedDate.monthNumber
    val selectedDay = selectedDate.dayOfMonth

    val toDoList by viewModel.todoList.collectAsState()


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            // Date Part
            DateText(
                month = month,
                day = day,
                modifier = Modifier.padding(top = 24.dp, start = 23.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            WeekCalendar(
                localDateTime = localDateTime,
                selectedDate = selectedDate,
                dateClickable = { viewModel.updateSelectedDate(it) },
                modifier = Modifier.padding(vertical = (12.5).dp, horizontal = 40.dp)
            )


            // progress status
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 17.dp)
            ) {
                DayProgress(
                    modifier = Modifier
                        .shadow(2.dp, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    month = selectedMonth,
                    day = selectedDay,
                    toDoCount = 10, // 임시 하드코딩
                    doneCount = 3
                )
            }



            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (28.5.dp), vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(toDoList.size) { idx ->
                    val toDo = toDoList[idx]

                    ToDoCard(toDoItem = toDo)


                }
            }

        }
    }
}

@Composable
fun DateText(
    modifier: Modifier = Modifier,
    month: Int,
    day: Int
) {
    Text(
        text = "${month}월 ${day}일",
        fontFamily = pretendard(),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        modifier = modifier
    )
}

@Composable
fun WeekCalendar(
    localDateTime: LocalDateTime,
    selectedDate: LocalDate,
    dateClickable: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentDayOfWeek = (localDateTime.dayOfWeek.ordinal + 1) % 7
    val currentDay = localDateTime.dayOfMonth

    val weekDays = (0..6).map { offset ->
        val day = currentDay - currentDayOfWeek + offset
        day
    }

    val dayNames = listOf("일", "월", "화", "수", "목", "금", "토")

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        weekDays.forEachIndexed { index, day ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(25))
                    .background(if (day == selectedDate.dayOfMonth) MainColor.copy(alpha = 0.1f) else Color.White)
                    .padding(vertical = 10.dp)
                    .clickable { dateClickable(LocalDate(localDateTime.year, localDateTime.monthNumber, day)) }

            ) {
                Text(
                    text = dayNames[index],
                    fontFamily = pretendard(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = when(index) {
                        0 -> Color.Red      // 일
                        6 -> Color.Blue     // 토
                        else -> Color.Black
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = day.toString(),
                    fontFamily = pretendard(),
                    fontWeight = if (currentDayOfWeek == index) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 14.sp,
                    color = if (currentDayOfWeek == index) Color.Black else Color.Gray
                )
            }

        }
    }
}

@Composable
fun DayProgress(
    modifier: Modifier = Modifier,
    month: Int,
    day: Int,
    toDoCount: Int,
    doneCount: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${month}월 ${day}일 진행 상황",
                    fontFamily = pretendard(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${doneCount}/${toDoCount}",
                    fontFamily = pretendard(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            LinearProgressIndicator(
                progress = { doneCount.toFloat() / toDoCount.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                strokeCap = StrokeCap.Butt,
                gapSize = 0.dp,
                color = MainColor,
                trackColor = Color.Gray,
                drawStopIndicator = { } // 우측 진행 빈 람다를 통해 원 제거
            )
        }
    }
}
