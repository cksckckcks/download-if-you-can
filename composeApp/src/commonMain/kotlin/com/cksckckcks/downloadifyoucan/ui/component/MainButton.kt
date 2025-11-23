package com.cksckckcks.downloadifyoucan.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cksckckcks.downloadifyoucan.theme.MainColor
import com.cksckckcks.downloadifyoucan.theme.pretendard
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview(showBackground = true)
@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text: String = "Button",
    enable: Boolean,
    buttonClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(if (enable) MainColor else Color.Gray)
            .clickable { if (enable) buttonClick() else Unit },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = pretendard(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 20.dp)
        )
    }
}