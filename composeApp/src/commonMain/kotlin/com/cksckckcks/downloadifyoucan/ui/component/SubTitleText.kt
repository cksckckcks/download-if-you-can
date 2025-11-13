package com.cksckckcks.downloadifyoucan.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cksckckcks.downloadifyoucan.theme.pretendard

@Composable
fun SubTitleText(
    text: String
) {
    Text(
        text = text,
        fontFamily = pretendard(),
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    )
}