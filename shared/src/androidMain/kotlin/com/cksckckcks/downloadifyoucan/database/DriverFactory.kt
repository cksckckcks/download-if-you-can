package com.cksckckcks.downloadifyoucan.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DriverFactory actual constructor(context: Any?) {
    private val appContext = context as Context

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = DownloadIfYouCanDatabase.Schema,
            context = appContext,
            name = "todo.db"
        )
    }
}