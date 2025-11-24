package com.cksckckcks.downloadifyoucan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.cksckckcks.downloadifyoucan.di.appModule
import com.cksckckcks.downloadifyoucan.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(applicationContext)
            modules(appModule, platformModule)
        }
        setContent {
            AndroidApp()
        }
    }
}

@Composable
fun AndroidApp() {
    App()
}