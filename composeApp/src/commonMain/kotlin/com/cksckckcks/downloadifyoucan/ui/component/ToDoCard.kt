package com.cksckckcks.downloadifyoucan.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cksckckcks.downloadifyoucan.model.ToDo
import com.cksckckcks.downloadifyoucan.theme.SubBackgroundColor
import com.cksckckcks.downloadifyoucan.theme.SubFontColor
import com.cksckckcks.downloadifyoucan.theme.pretendard
import downloadifyoucan.composeapp.generated.resources.Res
import downloadifyoucan.composeapp.generated.resources.ic_checked
import downloadifyoucan.composeapp.generated.resources.ic_trash
import downloadifyoucan.composeapp.generated.resources.ic_unchecked
import org.jetbrains.compose.resources.painterResource

@Composable
fun ToDoCard(
    toDoItem: ToDo,
    onCheckClick: () -> Unit = {},
    onCardClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = if (toDoItem.isDone)
                painterResource(Res.drawable.ic_checked)
            else
                painterResource(Res.drawable.ic_unchecked),
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .clickable {
                    onCheckClick()
                }
        )

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(5.dp),
                    color = Color(0xFFCFCFCF)
                )
                .clickable {
                    onCardClick()
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // 날짜, 중요도, 삭제 버튼
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    DateCard(toDoItem.year, toDoItem.month, toDoItem.day)

                    Spacer(modifier = Modifier.width(3.dp))

                    Image(
                        painter = painterResource(toDoItem.priority.image),
                        contentDescription = "중요도",
                        modifier = Modifier
                            .size(24.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        painter = painterResource(Res.drawable.ic_trash),
                        contentDescription = "할 일 삭제",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onDeleteClick()
                            }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // 제목
                Text(
                    text = toDoItem.title,
                    fontFamily = pretendard(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp,
                    color = Color.Black,
                )
            }
        }
    }
}

@Composable
fun DateCard(
    year: Int,
    month: Int,
    day: Int,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(SubBackgroundColor)
    ) {
        Text(
            text = "$year/$month/$day",
            fontFamily = pretendard(),
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            color = SubFontColor,
            modifier = Modifier
                .padding(horizontal = 7.dp, vertical = 5.dp)
        )
    }
}
