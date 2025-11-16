package com.cksckckcks.downloadifyoucan.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DriverFactory actual constructor(context: Any?) {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            DownloadIfYouCanDatabase.Schema,
            "todo.db"
        )
    }
}