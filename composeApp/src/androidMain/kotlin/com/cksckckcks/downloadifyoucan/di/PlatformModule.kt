package com.cksckckcks.downloadifyoucan.di

import com.cksckckcks.downloadifyoucan.database.DriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val platformModule = module {
    single { DriverFactory(androidContext()) }
}