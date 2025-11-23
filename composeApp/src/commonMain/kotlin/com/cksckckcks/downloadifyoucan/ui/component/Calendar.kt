import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cksckckcks.downloadifyoucan.theme.MainColor
import com.cksckckcks.downloadifyoucan.theme.pretendard
import kotlinx.datetime.*
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview(showBackground = true)
@Composable
fun Calendar(
    today: LocalDate = LocalDate(2025,11,1),
    selectedDate: LocalDate = LocalDate(2025,11,1),
    onDateClick: (LocalDate) -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { 12 })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) { page ->
        val monthOffset = page
        val targetDate = today.plus(monthOffset, DateTimeUnit.MONTH)
        val pageYear = targetDate.year
        val pageMonth = targetDate.monthNumber

        val firstDayOfMonth = LocalDate(pageYear, pageMonth, 1)
        val dayOfWeek = firstDayOfMonth.dayOfWeek.isoDayNumber
        val daysInMonth = firstDayOfMonth.daysUntil(
            firstDayOfMonth.plus(1, DateTimeUnit.MONTH)
        )

        val days = mutableListOf<LocalDate?>()

        if (dayOfWeek != 7) {
            repeat(dayOfWeek) { days.add(null) }
        }

        for (day in 1..daysInMonth) {
            days.add(LocalDate(pageYear, pageMonth, day))
        }

        while (days.size % 7 != 0) {
            days.add(null)
        }

        val weeks = days.chunked(7)

        Column {
            Text(
                text = "${pageYear}년 ${pageMonth}월",
                fontFamily = pretendard(),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            weeks.forEach { week ->
                WeekRow(
                    week = week,
                    selectedDate = selectedDate,
                    onDateClick = onDateClick
                )
            }
        }
    }
}

@Composable
fun WeekRow(
    week: List<LocalDate?>,
    selectedDate: LocalDate,
    onDateClick: (LocalDate) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        week.forEach { date ->
            DayBox(
                date = date,
                selectedDate = selectedDate,
                onDateClick = onDateClick
            )
        }
    }
}

@Composable
fun DayBox(
    date: LocalDate?,
    selectedDate: LocalDate,
    onDateClick: (LocalDate) -> Unit
) {
    val selected = date == selectedDate

    Box(
        modifier = Modifier
            .size(38.dp)
            .padding(vertical = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (selected) MainColor else Color.Transparent,
                    shape = RectangleShape
                )
                .clickable {
                    if (date != null) {
                        onDateClick(date)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = date?.dayOfMonth?.toString() ?: "",
                fontFamily = pretendard(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = if (selected) Color.White else Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}