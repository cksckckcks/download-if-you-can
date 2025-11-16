package com.cksckckcks.downloadifyoucan.database

class ToDoDataBase(driverFactory: DriverFactory) {
    private val database = DownloadIfYouCanDatabase(driverFactory.createDriver)
}