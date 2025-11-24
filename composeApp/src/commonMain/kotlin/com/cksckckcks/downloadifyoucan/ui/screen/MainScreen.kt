package com.cksckckcks.downloadifyoucan.ui.screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.cksckckcks.downloadifyoucan.model.ToDo
import com.cksckckcks.downloadifyoucan.theme.MainColor
import com.cksckckcks.downloadifyoucan.theme.pretendard
import com.cksckckcks.downloadifyoucan.ui.component.ToDoCard
import com.cksckckcks.downloadifyoucan.viewModel.MainViewModel
import downloadifyoucan.composeapp.generated.resources.Res
import downloadifyoucan.composeapp.generated.resources.ic_add
import downloadifyoucan.composeapp.generated.resources.logo_angry
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class MainScreen : Screen {
    @OptIn(ExperimentalTime::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: MainViewModel = koinInject()

        MainScreenContent(
            viewModel = viewModel,
            onAddClick = { navigator.push(AddToDoScreen()) },
            onToDoClick = { navigator.push(ToDoDetailScreen(it))}
        )
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun MainScreenContent(
    viewModel: MainViewModel,
    onAddClick: () -> Unit,
    onToDoClick: (ToDo) -> Unit
) {
    val localDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
    val month = localDateTime.monthNumber
    val day = localDateTime.dayOfMonth

    val selectedDate by viewModel.selectedDate.collectAsState()
    val selectedMonth = selectedDate.monthNumber
    val selectedDay = selectedDate.dayOfMonth

    val toDoCount by viewModel.todoCount.collectAsState()
    val doneCount by viewModel.todoDoneCount.collectAsState()
    val todos by viewModel.todos.collectAsState()


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        containerColor = Color.White,
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_add),
                    contentDescription = "추가",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable {
                            onAddClick()
                        }
                )
            }
        }
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
            if (todos.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.logo_angry),
                            contentDescription = "할 일 없음",
                            modifier = Modifier
                                .size(300.dp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "할 일이 없어요!",
                            fontFamily = pretendard(),
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
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
                    toDoCount = toDoCount,
                    doneCount = doneCount
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (28.5.dp), vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(todos.size) { idx ->
                    val toDo = todos[idx]

                    ToDoCard(
                        toDoItem = toDo,
                        onCardClick = { onToDoClick(toDo) },
                        onDeleteClick = { viewModel.todoDelete(toDo.id) },
                        onCheckClick = { viewModel.todoDoneToggle(toDo.id, !toDo.isDone)}
                    )
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
    // 큰 숫자 넣어두기
    val initialPage = 100
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { 200 }
    )

    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { page ->
        // 현재 페이지에서 몇 주 떨어져 있는지 계산
        val weekOffset = page - initialPage

        WeekCalendarItem(
            localDateTime = localDateTime,
            selectedDate = selectedDate,
            weekOffset = weekOffset,
            dateClickable = dateClickable
        )
    }
}
@Composable
fun WeekCalendarItem(
    localDateTime: LocalDateTime,
    selectedDate: LocalDate,
    dateClickable: (LocalDate) -> Unit,
    weekOffset: Int
) {
    val today = localDateTime.date
    val currentDayOfWeek = (localDateTime.dayOfWeek.ordinal + 1) % 7

    val weekStartDate = today
        .minus(currentDayOfWeek, DateTimeUnit.DAY)  // 이번 주 일요일
        .plus(weekOffset * 7, DateTimeUnit.DAY)

    val weekDays = (0..6).map { offset ->
        weekStartDate.plus(offset, DateTimeUnit.DAY)
    }

    val dayNames = listOf("일", "월", "화", "수", "목", "금", "토")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        weekDays.forEachIndexed { index, date ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(25))
                    .background(if (date == selectedDate) MainColor.copy(alpha = 0.1f) else Color.White)
                    .padding(vertical = 10.dp)
                    .clickable { dateClickable(date) }

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
                    text = date.day.toString(),
                    fontFamily = pretendard(),
                    fontWeight = if (date == selectedDate) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 14.sp,
                    color = if (date == selectedDate) Color.Black else Color.Gray
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
