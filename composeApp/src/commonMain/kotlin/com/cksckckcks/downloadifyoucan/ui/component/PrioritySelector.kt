package com.cksckckcks.downloadifyoucan.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cksckckcks.downloadifyoucan.model.Priority
import com.cksckckcks.downloadifyoucan.model.image
import com.cksckckcks.downloadifyoucan.theme.MainColor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview(showBackground = true)
@Composable
fun PrioritySelector(
    selectedPriority: Priority = Priority.MEDIUM
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Priority.entries.forEach { priority ->
            val backgroundColor = if (priority == selectedPriority) MainColor.copy(0.3f) else Color.Unspecified
            Image(
                painter = painterResource(priority.image),
                contentDescription = priority.name,
                    modifier = Modifier
                        .size(100.dp)
                        .background(backgroundColor)
            )
        }
    }

}