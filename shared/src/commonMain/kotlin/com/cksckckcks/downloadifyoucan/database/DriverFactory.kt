package com.cksckckcks.downloadifyoucan.database

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory(context: Any? = null) {
    fun createDriver(): SqlDriver
}