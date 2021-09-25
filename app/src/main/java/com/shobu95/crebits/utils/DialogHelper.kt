package com.shobu95.crebits.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*


class DatePickerFragment(private val clickListener: DatePickerListener) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        var date = "$day/$month/$year"
        clickListener.onDateSelected(date)
    }
}


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
        val formatter = SimpleDateFormat(Constants.TIME_FORMAT_H_MM_A)
        clickListener.onTimeSelected(formatter.format(time))

    }
}


class TwoButtonDialogFragment(
    private val message: String,
    private val positiveBtnString: String,
    private val negativeBtnString: String,
    private val clickListener: DialogButtonListener
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(message)
                .setPositiveButton(positiveBtnString) { dialog, id ->
                    clickListener.onPositiveClicked()
                }
                .setNegativeButton(negativeBtnString) { dialog, id ->
                    clickListener.onNegativeClicked()// User cancelled the dialog
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}


interface TimePickerListener {
    fun onTimeSelected(time: String)
}

interface DatePickerListener {
    fun onDateSelected(date: String)
}

interface DialogButtonListener {
    fun onPositiveClicked()
    fun onNegativeClicked()
}