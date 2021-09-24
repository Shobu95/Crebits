package com.shobu95.crebits.utils.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

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

interface DialogButtonListener {
    fun onPositiveClicked()
    fun onNegativeClicked()
}