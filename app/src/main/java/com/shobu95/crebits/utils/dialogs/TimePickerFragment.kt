package com.shobu95.crebits.utils.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class TimePickerFragment(private val clickListener: TimePickerListener) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, false)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        val time = Time(hourOfDay, minute, 0)
        val formatter = SimpleDateFormat("h:mm:a")
        clickListener.onTimeSelected(formatter.format(time))

    }
}

interface TimePickerListener {
    fun onTimeSelected(time: String)
}