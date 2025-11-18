package com.cksckckcks.downloadifyoucan

import androidx.compose.runtime.*
import com.cksckckcks.downloadifyoucan.database.DriverFactory
import com.cksckckcks.downloadifyoucan.database.ToDoDataBase
import com.cksckckcks.downloadifyoucan.ui.screen.MainScreen
import com.cksckckcks.downloadifyoucan.viewModel.MainViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import downloadifyoucan.composeapp.generated.resources.Res
import downloadifyoucan.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(driverFactory: DriverFactory) {
    val viewModel = MainViewModel(
        dataBase = ToDoDataBase(driverFactory)
    )

    MainScreen(viewModel)
}