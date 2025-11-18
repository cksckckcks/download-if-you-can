package com.cksckckcks.downloadifyoucan

import androidx.compose.ui.window.ComposeUIViewController
import com.cksckckcks.downloadifyoucan.database.DriverFactory

fun MainViewController() = ComposeUIViewController {
    App(
        driverFactory = DriverFactory(null)
    )
}
