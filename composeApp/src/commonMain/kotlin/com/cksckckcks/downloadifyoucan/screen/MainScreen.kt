package com.cksckckcks.downloadifyoucan.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cksckckcks.downloadifyoucan.theme.pretendard
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock

@Preview(showBackground = true)
@OptIn(kotlin.time.ExperimentalTime::class)
@Composable
fun MainScreen() {
    val localDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
    val month = localDateTime.monthNumber
    val day = localDateTime.dayOfMonth

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
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
        val day = currentDay - currentDayOfWeek + 1 + offset
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
                    fontWeight = if (currentDayOfWeek == index + 1) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 14.sp,
                    color = if (currentDayOfWeek == index + 1) Color.Black else Color.Gray
                )
            }
        }
    }
}