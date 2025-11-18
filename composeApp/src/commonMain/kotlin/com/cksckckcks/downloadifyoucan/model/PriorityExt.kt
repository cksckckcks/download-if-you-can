package com.cksckckcks.downloadifyoucan.model

import downloadifyoucan.composeapp.generated.resources.Res
import downloadifyoucan.composeapp.generated.resources.todo_high
import downloadifyoucan.composeapp.generated.resources.todo_low
import downloadifyoucan.composeapp.generated.resources.todo_medium
import org.jetbrains.compose.resources.DrawableResource

val Priority.image: DrawableResource
    get() = when (this) {
        Priority.LOW -> Res.drawable.todo_low
        Priority.MEDIUM -> Res.drawable.todo_medium
        Priority.HIGH -> Res.drawable.todo_high
    }