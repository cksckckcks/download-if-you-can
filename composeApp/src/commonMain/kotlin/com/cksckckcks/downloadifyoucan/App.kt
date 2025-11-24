package com.cksckckcks.downloadifyoucan

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.cksckckcks.downloadifyoucan.ui.screen.MainScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    Navigator(MainScreen())
}
