package com.shobu95.crebits.screens.crebit_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shobu95.crebits.database.TransactionDatabaseDao
import com.shobu95.crebits.database.entities.Transaction
import com.shobu95.crebits.utils.dialogs.DialogButtonListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CrebitListViewModel(val database: TransactionDatabaseDao) : ViewModel() {

    val transactions = database.getAll()

    private var _showDeleteSnackBar = MutableLiveData<Boolean>()
    val showDeleteSnackBar: LiveData<Boolean> get() = _showDeleteSnackBar

    fun setDeleteDialogListener(transaction: Transaction): DialogButtonListener {
        val clickListener = object : DialogButtonListener {
            override fun onPositiveClicked() {
                deleteTransaction(transaction)
                _showDeleteSnackBar.value = true
            }

            override fun onNegativeClicked() {
                // just close dialog
            }

        }
        return clickListener
    }


    private fun deleteTransaction(transaction: Transaction) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                database.delete(transaction)
            }
        }
    }

    fun onSnackBarShown() {
        _showDeleteSnackBar.value = false
    }

}