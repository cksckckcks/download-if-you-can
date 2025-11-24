package com.cksckckcks.downloadifyoucan.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cksckckcks.downloadifyoucan.model.Priority
import com.cksckckcks.downloadifyoucan.model.ToDo
import com.cksckckcks.downloadifyoucan.model.image
import com.cksckckcks.downloadifyoucan.theme.pretendard
import com.cksckckcks.downloadifyoucan.ui.component.DateBox
import com.cksckckcks.downloadifyoucan.ui.component.TitleText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

class ToDoDetailScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val tmpToDo = ToDo(
            id = 1,
            title = "디자인하기",
            description = "UI 완성하기\nUX신경쓰기\n123123123123123123123123123123123123",
            year = 2025,
            month = 11,
            day = 24,
            priority = Priority.MEDIUM,
            isDone = false
        )

        ToDoDetailScreenContent(
            todo = tmpToDo
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoDetailScreenContent(
    todo: ToDo
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp)
        ) {
            TitleText(
                text = "할일 상세정보",
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 37.dp)
            )

            DateBox(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color(0xFFF3F4F6)),
                date = "${todo.year}년 ${todo.month}월 ${todo.day}일",
                textColor = Color(0xFF6B7280)
            )

            Spacer(modifier = Modifier.height(44.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = todo.title,
                    fontFamily = pretendard(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 36.sp,
                )

                Image(
                    painter = painterResource(todo.priority.image),
                    contentDescription = "중요도 사진",
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = todo.description,
                fontFamily = pretendard(),
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp),
            )
        }
    }
}
