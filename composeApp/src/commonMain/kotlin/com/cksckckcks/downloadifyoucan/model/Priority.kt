package com.cksckckcks.downloadifyoucan.model

import downloadifyoucan.composeapp.generated.resources.Res
import downloadifyoucan.composeapp.generated.resources.todo_high
import downloadifyoucan.composeapp.generated.resources.todo_low
import downloadifyoucan.composeapp.generated.resources.todo_medium
import org.jetbrains.compose.resources.DrawableResource

enum class Priority(
    val image: DrawableResource
) {
    LOW(Res.drawable.todo_low),
    MEDIUM(Res.drawable.todo_medium),
    HIGH(Res.drawable.todo_high)
}
// 추후 Imege 추가해두기
