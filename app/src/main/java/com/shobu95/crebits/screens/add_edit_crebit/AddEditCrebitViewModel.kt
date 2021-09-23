package com.shobu95.crebits.screens.add_edit_crebit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shobu95.crebits.database.TransactionDatabaseDao
import com.shobu95.crebits.database.entities.Transaction
import com.shobu95.crebits.utils.Constants
import com.shobu95.crebits.utils.dialogs.DatePickerListener
import com.shobu95.crebits.utils.dialogs.TimePickerListener
import com.shobu95.crebits.utils.enums.TransactionType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddEditCrebitViewModel(
    val transaction: Transaction?,
    val database: TransactionDatabaseDao
) : ViewModel() {

    val transactionType = MutableLiveData<String>()
    val amount = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private var _screenState = MutableLiveData<String>()
    val screenState: LiveData<String> get() = _screenState

    private var _openDatePicker = MutableLiveData<Boolean>()
    val openDatePicker: LiveData<Boolean> get() = _openDatePicker

    private var _openTimePicker = MutableLiveData<Boolean>()
    val openTimePicker: LiveData<Boolean> get() = _openTimePicker

    private var _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean> get() = _navigateToList

    private var _showSnackBarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean> get() = _showSnackBarEvent

    init {
        if (transaction != null) {
            setData()
            _screenState.value = Constants.SCREEN_STATE_EDIT
        } else {
            _screenState.value = Constants.SCREEN_STATE_ADD
        }
    }

    fun setTransactionType(type: TransactionType) {
        transactionType.value = type.name
    }

    fun openDatePicker() {
        _openDatePicker.value = true
    }

    fun onDatePickerClose() {
        _openDatePicker.value = false
    }

    fun openTimePicker() {
        _openTimePicker.value = true
    }

    fun onTimePickerClosed() {
        _openTimePicker.value = false
    }

    private fun setData() {
        transactionType.value = transaction?.type
        amount.value = transaction?.amount
        date.value = transaction?.date
        time.value = transaction?.time
        description.value = transaction?.description
    }

    fun setDatePickerListener(): DatePickerListener {
        val clickListener = object : DatePickerListener {
            override fun onDateSelected(_date: String) {
                date.value = _date
                onDatePickerClose()
            }
        }
        return clickListener
    }

    fun setTimePickerListener(): TimePickerListener {
        val clickListener = object : TimePickerListener {
            override fun onTimeSelected(_time: String) {
                time.value = _time
                onTimePickerClosed()
            }
        }
        return clickListener
    }


    fun saveTransaction() {
        CoroutineScope(Dispatchers.Main).launch {
            val transaction = Transaction()
            transaction.type = transactionType.value
            transaction.amount = amount.value
            transaction.date = date.value
            transaction.time = time.value
            transaction.description = description.value
            save(transaction)
            _navigateToList.value = true
        }
    }

    private suspend fun save(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            database.save(transaction)
        }
    }

    fun onNavigateToListScreenComplete() {
        _navigateToList.value = false
        _showSnackBarEvent.value = true
    }

    fun doneShowingSnackbar() {
        _showSnackBarEvent.value = false
    }


}