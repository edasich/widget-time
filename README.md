# Android Widgets Time
[![](https://jitpack.io/v/edasich/widget-time.svg)](https://jitpack.io/#edasich/widget-time)

### Dependency
```gradle
implementation 'com.github.edasich:widget-time:LATEST_VERSION'
```

### How to use it ?

Add `DateTimeSwitcher` in xml.

```xml
<com.github.edasich.widget.time.DateTimeSwitcher
    android:id="@+id/dateTimeSwitcher"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

### Change Switch Mode
Currently, supported modes are Day, Week, Month and Year.
To change the mode just set the new mode to `DateTimeSwitcher` :
```kotlin
dateTimeSwitcher.switchMode = SwitchMode.DAY
```

### Get Your Start and End Dates
When mode is changed, start and end dates are evaluated based on mode.

### Set Your Custom Adapter 
When dates are being changed by pressing `next` and `previous` buttons, `title` is updated based on evaluated `start` and `end` dates.
To apply your custom date format for `title`, use `DateTimeSwitcherAdapter`.

```kotlin
dateTimeSwitcher.adapter = object : DateTimeSwitcherAdapter() {
    override fun getTitle(
        switchMode: SwitchMode,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): String {
        ..
    }
}
```

### Switch Mode Listener
```kotlin
dateTimeSwitcher.setOnSwitchChangedListener { switchMode, startDateTime, endDateTime ->
    ..
}
```

### Next and Previous Listeners
```kotlin
dateTimeSwitcher.setOnNextClickListener { switchMode, startDateTime, endDateTime ->
    ..
}
dateTimeSwitcher.setOnPreviousClickListener { switchMode, startDateTime, endDateTime ->
    ..
}
```

### Note 
This library is using `LocalDateTime`, `LocalDate` and `LocalTime` which are part of `java.time` package.

In order to use this library please read this official doc [core android library desugaring](https://developer.android.com/studio/write/java8-support#library-desugaring).

