package com.cksckckcks.downloadifyoucan

import androidx.compose.ui.window.ComposeUIViewController
import com.cksckckcks.downloadifyoucan.di.appModule
import com.cksckckcks.downloadifyoucan.di.platformModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(appModule, platformModule)
    }
    App()
}
