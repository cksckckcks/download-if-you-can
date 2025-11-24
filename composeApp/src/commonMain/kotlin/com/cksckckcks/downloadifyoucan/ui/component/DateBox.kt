package com.cksckckcks.downloadifyoucan.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cksckckcks.downloadifyoucan.theme.pretendard


@Composable
fun DateBox(
    modifier: Modifier = Modifier,
    date: String,
    textColor: Color
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = date,
            fontFamily = pretendard(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 7.dp)
        )
    }
}