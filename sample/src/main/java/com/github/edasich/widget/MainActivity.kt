package com.github.edasich.widget

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.edasich.time.toEpochMilli
import com.github.edasich.widget.databinding.ActivityMainBinding
import com.github.edasich.widget.time.SwitchMode

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = ActivityMainBinding.inflate(layoutInflater)
        setContentView(layout.root)

        layout.switcher.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnDay -> {
                        layout.dateTimeSwitcher.switchMode = SwitchMode.DAY
                    }
                    R.id.btnWeek -> {
                        layout.dateTimeSwitcher.switchMode = SwitchMode.WEEK
                    }
                    R.id.btnMonth -> {
                        layout.dateTimeSwitcher.switchMode = SwitchMode.MONTH
                    }
                    R.id.btnYear -> {
                        layout.dateTimeSwitcher.switchMode = SwitchMode.YEAR
                    }
                }
            }
        }
        layout.dateTimeSwitcher.setOnSwitchChangedListener { switchMode, startDateTime, endDateTime ->
            Log.i(
                TAG,
                "SwitchMode : $switchMode start : ${startDateTime.toEpochMilli()} end : ${endDateTime.toEpochMilli()}"
            )
        }
        layout.dateTimeSwitcher.setOnNextClickListener { switchMode, startDateTime, endDateTime ->
            Log.i(
                TAG,
                "NEXT : switchMode : $switchMode start : ${startDateTime.toEpochMilli()} end : ${endDateTime.toEpochMilli()}"
            )
        }
        layout.dateTimeSwitcher.setOnPreviousClickListener { switchMode, startDateTime, endDateTime ->
            Log.i(
                TAG,
                "PREVIOUS : switchMode : $switchMode start : ${startDateTime.toEpochMilli()} end : ${endDateTime.toEpochMilli()}"
            )
        }
    }

}