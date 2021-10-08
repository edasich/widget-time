package com.github.edasich.widget.time

import java.time.LocalDateTime

abstract class DateTimeSwitcherAdapter {

    abstract fun getTitle(
        switchMode: SwitchMode,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): String

}