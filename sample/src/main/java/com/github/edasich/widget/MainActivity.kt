package com.github.edasich.widget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.edasich.widget.databinding.ActivityMainBinding
import com.github.edasich.widget.time.SwitchMode

class MainActivity : AppCompatActivity() {

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
        }
        layout.dateTimeSwitcher.setOnNextClickListener { switchMode, startDateTime, endDateTime ->
        }
        layout.dateTimeSwitcher.setOnPreviousClickListener { switchMode, startDateTime, endDateTime ->
        }
    }

}