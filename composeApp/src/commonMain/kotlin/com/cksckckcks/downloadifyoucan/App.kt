package com.cksckckcks.downloadifyoucan

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.cksckckcks.downloadifyoucan.ui.screen.MainScreen
import com.cksckckcks.downloadifyoucan.ui.screen.SplashScreen
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // 2초
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        Navigator(MainScreen())
    }
}
