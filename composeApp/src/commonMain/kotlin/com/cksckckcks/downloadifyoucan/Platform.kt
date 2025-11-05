package com.cksckckcks.downloadifyoucan

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform