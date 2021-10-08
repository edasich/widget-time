package com.github.edasich.widget.time.internal

import android.content.Context
import com.github.edasich.time.DateTime
import com.github.edasich.widget.time.DateTimeSwitcherAdapter
import com.github.edasich.widget.time.R
import com.github.edasich.widget.time.SwitchMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

internal class DefaultDateTimeSwitcherAdapter(val context: Context) : DateTimeSwitcherAdapter() {

    override fun getTitle(
        switchMode: SwitchMode,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): String {
        return when (switchMode) {
            SwitchMode.DAY -> {
                if (startDateTime.toLocalDate().isEqual(DateTime.getCurrentDate())) {
                    context.getString(R.string.today)
                } else {
                    startDateTime.dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault()
                    )
                }
            }
            SwitchMode.WEEK -> {
                endDateTime.format(DateTimeFormatter.ofPattern("dd MMM"))
            }
            SwitchMode.MONTH -> {
                endDateTime.format(DateTimeFormatter.ofPattern("MMM YYYY"))
            }
            SwitchMode.YEAR -> {
                endDateTime.format(DateTimeFormatter.ofPattern("YYYY"))
            }
        }
    }

}