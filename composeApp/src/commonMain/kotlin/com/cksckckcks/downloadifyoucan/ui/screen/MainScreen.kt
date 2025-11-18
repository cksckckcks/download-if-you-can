package com.cksckckcks.downloadifyoucan.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cksckckcks.downloadifyoucan.model.Priority
import com.cksckckcks.downloadifyoucan.model.ToDo
import com.cksckckcks.downloadifyoucan.theme.MainColor
import com.cksckckcks.downloadifyoucan.theme.pretendard
import com.cksckckcks.downloadifyoucan.ui.component.ToDoCard
import com.cksckckcks.downloadifyoucan.viewModel.MainViewModel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Preview(showBackground = true)
@OptIn(ExperimentalTime::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val localDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
    val month = localDateTime.monthNumber
    val day = localDateTime.dayOfMonth

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
                    month = month,
                    day = day,
                    toDoCount = 10, // 임시 하드코딩
                    doneCount = 3
                )
            }

            val tmpToDoList = listOf(
                ToDo(
                    id = 1,
                    title = "디자인 하기",
                    description = "디자인~",
                    year = 2025,
                    month = 1,
                    day = 1,
                    priority = Priority.MEDIUM,
                    isDone = false
                ),
                ToDo(
                    id = 2,
                    title = "디자인 하기",
                    description = "디자인~",
                    year = 2025,
                    month = 1,
                    day = 1,
                    priority = Priority.HIGH,
                    isDone = false
                ),
                ToDo(
                    id = 3,
                    title = "디자인 하기",
                    description = "디자인~",
                    year = 2025,
                    month = 1,
                    day = 1,
                    priority = Priority.LOW,
                    isDone = false
                )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (28.5.dp), vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(tmpToDoList.size) { idx ->
                    val toDo = tmpToDoList[idx]

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
    modifier: Modifier = Modifier
) {
    val currentDayOfWeek = localDateTime.dayOfWeek.ordinal + 1
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
                modifier = Modifier.weight(1f)
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
