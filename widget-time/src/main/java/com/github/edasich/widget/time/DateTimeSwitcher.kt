package com.github.edasich.widget.time

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.edasich.time.DateTime
import com.github.edasich.widget.time.internal.DefaultDateTimeSwitcherAdapter
import com.google.android.material.textview.MaterialTextView
import java.time.LocalDateTime
import java.time.LocalTime

class DateTimeSwitcher : ConstraintLayout {

    var adapter: DateTimeSwitcherAdapter = DefaultDateTimeSwitcherAdapter(context = context)
        set(value) {
            field = value
            updateTitle()
        }
    var switchMode: SwitchMode = SwitchMode.DAY
        set(value) {
            field = value
            resetDates()
            updateTitle()
        }
    lateinit var startDateTime: LocalDateTime
    lateinit var endDateTime: LocalDateTime

    private var switchChangedListener: OnSwitchChangedListener? = null
    private var nextClickListener: OnNextClickListener? = null
    private var previousClickListener: OnPreviousClickListener? = null

    private lateinit var textTitle: MaterialTextView
    private lateinit var btnNext: View
    private lateinit var btnPrevious: View

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val layout = inflate(
            context,
            R.layout.layout_date_time_switcher,
            this
        )
        textTitle = layout.findViewById(R.id.textTitle)
        btnNext = layout.findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            next()
        }
        btnPrevious = layout.findViewById(R.id.btnPrevious)
        btnPrevious.setOnClickListener {
            previous()
        }

        switchMode = SwitchMode.DAY
    }

    fun setOnSwitchChangedListener(listener: OnSwitchChangedListener) {
        switchChangedListener = listener
    }

    fun setOnNextClickListener(listener: OnNextClickListener) {
        nextClickListener = listener
    }

    fun setOnPreviousClickListener(listener: OnPreviousClickListener) {
        previousClickListener = listener
    }

    fun resetDates() {
        when (switchMode) {
            SwitchMode.DAY -> {
                startDateTime = DateTime.getDateTime(
                    date = DateTime.getCurrentDate(),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = DateTime.getCurrentDate(),
                    time = LocalTime.MAX
                )
            }
            SwitchMode.WEEK -> {
                startDateTime = DateTime.getDateTime(
                    date = DateTime.getWeeksAgo(weeks = 1).toLocalDate(),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = DateTime.getCurrentDate(),
                    time = LocalTime.MAX
                )
            }
            SwitchMode.MONTH -> {
                val targetDateTime = DateTime.getCurrentDate()
                startDateTime = DateTime.getDateTime(
                    date = targetDateTime.withDayOfMonth(1),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = targetDateTime.withDayOfMonth(targetDateTime.lengthOfMonth()),
                    time = LocalTime.MAX
                )
            }
            SwitchMode.YEAR -> {
                startDateTime = DateTime.getDateTime(
                    date = DateTime.getYearsAgo(years = 1).toLocalDate(),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = DateTime.getCurrentDate(),
                    time = LocalTime.MAX
                )
            }
        }
        switchChangedListener?.onSwitchChanged(
            switchMode = switchMode,
            startDateTime = startDateTime,
            endDateTime = endDateTime
        )
    }

    private fun updateTitle() {
        textTitle.text = adapter.getTitle(
            switchMode = switchMode,
            startDateTime = startDateTime,
            endDateTime = endDateTime
        )
    }

    private fun next() {
        when (switchMode) {
            SwitchMode.DAY -> {
                startDateTime = DateTime.getDateTime(
                    date = DateTime.getDaysLater(days = 1, from = startDateTime).toLocalDate(),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = DateTime.getDaysLater(days = 1, from = endDateTime).toLocalDate(),
                    time = LocalTime.MAX
                )
            }
            SwitchMode.WEEK -> {
                startDateTime = DateTime.getDateTime(
                    date = DateTime.getWeeksLater(weeks = 1, from = startDateTime).toLocalDate(),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = DateTime.getWeeksLater(weeks = 1, from = endDateTime).toLocalDate(),
                    time = LocalTime.MAX
                )
            }
            SwitchMode.MONTH -> {
                val targetDate =
                    DateTime.getMonthsLater(months = 1, from = startDateTime).toLocalDate()
                startDateTime = DateTime.getDateTime(
                    date = targetDate.withDayOfMonth(1),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = targetDate.withDayOfMonth(targetDate.lengthOfMonth()),
                    time = LocalTime.MAX
                )
            }
            SwitchMode.YEAR -> {
                startDateTime = DateTime.getDateTime(
                    date = DateTime.getYearsLater(years = 1, from = startDateTime).toLocalDate(),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = DateTime.getYearsLater(years = 1, from = endDateTime).toLocalDate(),
                    time = LocalTime.MAX
                )
            }
        }
        updateTitle()
        nextClickListener?.onNextClicked(
            switchMode = switchMode,
            startDateTime = startDateTime,
            endDateTime = endDateTime
        )
    }

    private fun previous() {
        when (switchMode) {
            SwitchMode.DAY -> {
                startDateTime = DateTime.getDateTime(
                    date = DateTime.getDaysAgo(days = 1, from = startDateTime).toLocalDate(),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = DateTime.getDaysAgo(days = 1, from = endDateTime).toLocalDate(),
                    time = LocalTime.MAX
                )
            }
            SwitchMode.WEEK -> {
                startDateTime = DateTime.getDateTime(
                    date = DateTime.getWeeksAgo(weeks = 1, from = startDateTime).toLocalDate(),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = DateTime.getWeeksAgo(weeks = 1, from = endDateTime).toLocalDate(),
                    time = LocalTime.MAX
                )
            }
            SwitchMode.MONTH -> {
                val targetDate =
                    DateTime.getMonthsAgo(months = 1, from = startDateTime).toLocalDate()
                startDateTime = DateTime.getDateTime(
                    date = targetDate.withDayOfMonth(1),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = targetDate.withDayOfMonth(targetDate.lengthOfMonth()),
                    time = LocalTime.MAX
                )
            }
            SwitchMode.YEAR -> {
                startDateTime = DateTime.getDateTime(
                    date = DateTime.getYearsAgo(years = 1, from = startDateTime).toLocalDate(),
                    time = LocalTime.MIN
                )
                endDateTime = DateTime.getDateTime(
                    date = DateTime.getYearsAgo(years = 1, from = endDateTime).toLocalDate(),
                    time = LocalTime.MAX
                )
            }
        }
        updateTitle()
        previousClickListener?.onPreviousClicked(
            switchMode = switchMode,
            startDateTime = startDateTime,
            endDateTime = endDateTime
        )
    }

    fun interface OnSwitchChangedListener {
        fun onSwitchChanged(
            switchMode: SwitchMode,
            startDateTime: LocalDateTime,
            endDateTime: LocalDateTime
        )
    }

    fun interface OnNextClickListener {
        fun onNextClicked(
            switchMode: SwitchMode,
            startDateTime: LocalDateTime,
            endDateTime: LocalDateTime
        )
    }

    fun interface OnPreviousClickListener {
        fun onPreviousClicked(
            switchMode: SwitchMode,
            startDateTime: LocalDateTime,
            endDateTime: LocalDateTime
        )
    }

}