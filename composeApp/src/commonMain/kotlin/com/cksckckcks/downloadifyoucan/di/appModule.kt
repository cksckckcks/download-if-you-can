package com.cksckckcks.downloadifyoucan.di

import com.cksckckcks.downloadifyoucan.database.DriverFactory
import com.cksckckcks.downloadifyoucan.database.ToDoDataBase
import com.cksckckcks.downloadifyoucan.viewModel.AddToDoViewModel
import com.cksckckcks.downloadifyoucan.viewModel.MainViewModel
import org.koin.dsl.module

val appModule = module {
    // Database
    single { ToDoDataBase(get()) }

    // ViewModels
    factory { MainViewModel(get()) }
    factory { AddToDoViewModel(get()) }
}
