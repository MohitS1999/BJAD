package com.example.bjad.util

import java.util.concurrent.TimeUnit


fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun formatDuration(duration: Long) :String {
        val minutes = TimeUnit.MINUTES.convert(duration,TimeUnit.MILLISECONDS)
        val seconds = (TimeUnit.SECONDS.convert(duration,TimeUnit.MILLISECONDS) -
                minutes * TimeUnit.SECONDS.convert(1,TimeUnit.MINUTES))
        return String.format("%02d:%02d",minutes,seconds)
}

