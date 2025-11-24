package com.cksckckcks.downloadifyoucan.ui.screen

import Calendar
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cksckckcks.downloadifyoucan.ui.component.MainButton
import com.cksckckcks.downloadifyoucan.ui.component.MainInputField
import com.cksckckcks.downloadifyoucan.ui.component.PrioritySelector
import com.cksckckcks.downloadifyoucan.ui.component.SubTitleText
import com.cksckckcks.downloadifyoucan.ui.component.TitleText
import com.cksckckcks.downloadifyoucan.viewModel.AddToDoViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import kotlin.time.ExperimentalTime


class AddToDoScreen : Screen {
    @OptIn(ExperimentalTime::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AddToDoViewModel = koinInject()

        AddToDoScreenContent(
            viewModel = viewModel,
            onSaveSuccess = {
                viewModel.addTodo()
                navigator.pop()
            }
        )
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalTime::class)
@Composable
fun AddToDoScreenContent(
    viewModel: AddToDoViewModel,
    onSaveSuccess: () -> Unit
) {
    val selectedDate by viewModel.selectedDate.collectAsState()
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val priority by viewModel.priority.collectAsState()

    val itemSpaceValue = 21.dp


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
            TitleText(text = "할일 추가")

            Spacer(modifier = Modifier.height(39.dp))

            SubTitleText("날짜")
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Calendar(
                    today = selectedDate,
                    selectedDate = selectedDate,
                    onDateClick = {
                        viewModel.updateSelectedDate(it)
                    }
                )
            }

            Spacer(modifier = Modifier.height(itemSpaceValue))

            SubTitleText("제목")
            MainInputField(
                value =  title,
                onValueChange = {
                    viewModel.updateTitle(it)
                },
                placeholder = "할일을 입력하세요",
                singleLine = true
            )

            Spacer(modifier = Modifier.height(itemSpaceValue))

            SubTitleText("세부내용")
            MainInputField(
                value = description,
                onValueChange = {
                    viewModel.updateDescription(it)
                },
                placeholder = "할일을 입력하세요",
                singleLine = false
            )

            Spacer(modifier = Modifier.height(itemSpaceValue))

            SubTitleText("급함 정도")
            PrioritySelector(
                selectedPriority = priority,
                onPriorityClick = { viewModel.updatePriority(it) }
            )

            Spacer(modifier = Modifier.height(itemSpaceValue))

            MainButton(
                text = "할일 추가하기",
                enable = viewModel.checkInput(),
                buttonClick = {
                    onSaveSuccess()
                }
            )
        }
    }
}
