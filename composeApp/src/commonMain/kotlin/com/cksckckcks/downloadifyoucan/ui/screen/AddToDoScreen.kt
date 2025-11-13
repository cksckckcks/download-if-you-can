package com.cksckckcks.downloadifyoucan.ui.screen

import Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cksckckcks.downloadifyoucan.ui.component.MainButton
import com.cksckckcks.downloadifyoucan.ui.component.MainInputField
import com.cksckckcks.downloadifyoucan.ui.component.PrioritySelector
import com.cksckckcks.downloadifyoucan.ui.component.SubTitleText
import com.cksckckcks.downloadifyoucan.ui.component.TitleText
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@Preview(showBackground = true)
@OptIn(ExperimentalTime::class)
@Composable
fun AddToDoScreen(

) {
    val now: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp)
        ) {
            TitleText("할일 추가")

            Spacer(modifier = Modifier.height(39.dp))

            SubTitleText("날짜")
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Calendar(
                    today = now,
                    selectedDate = now
                )
            }

            Spacer(modifier = Modifier.height(21.dp))

            SubTitleText("제목")
            MainInputField(
                value = "",
                onValueChange = {},
                placeholder = "할일을 입력하세요",
                singleLine = true
            )

            Spacer(modifier = Modifier.height(21.dp))

            SubTitleText("세부내용")
            MainInputField(
                value = "",
                onValueChange = {},
                placeholder = "할일을 입력하세요",
                singleLine = false
            )

            Spacer(modifier = Modifier.height(21.dp))

            SubTitleText("급함 정도")
            PrioritySelector()

            Spacer(modifier = Modifier.height(21.dp))

            MainButton(text = "할일 추가하기")

        }
    }
}

