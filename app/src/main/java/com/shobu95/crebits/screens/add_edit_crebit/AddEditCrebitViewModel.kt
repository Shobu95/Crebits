package com.shobu95.crebits.screens.add_edit_crebit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu95.crebits.backend.repository.GeneralRepository
import com.shobu95.crebits.model.TransactionData
import com.shobu95.crebits.utils.Constants
import com.shobu95.crebits.utils.DatePickerListener
import com.shobu95.crebits.utils.TimePickerListener
import com.shobu95.crebits.utils.Constants.TransactionType
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEditCrebitViewModel @Inject constructor(val repository: GeneralRepository) : ViewModel() {

    private var transactionData: TransactionData? = null

    /*
    * Dealing with UI
    * */
    val transactionType = MutableLiveData<String>()
    val amount = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    /*
    * LifeData Variables
    * */
    private var _screenState = MutableLiveData<String>()
    val screenState: LiveData<String> get() = _screenState

    private var _openDatePicker = MutableLiveData<Boolean>()
    val openDatePicker: LiveData<Boolean> get() = _openDatePicker

    private var _openTimePicker = MutableLiveData<Boolean>()
    val openTimePicker: LiveData<Boolean> get() = _openTimePicker

    private var _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean> get() = _navigateToList

    private var _showSnackBarEvent = MutableLiveData<String>()
    val showSnackBarEvent: LiveData<String> get() = _showSnackBarEvent

    fun getTransactionData(transactionData: TransactionData?) {
        if (transactionData != null) {
            this.transactionData = transactionData
            setTransactionUIData(transactionData)
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

    fun onExecute() {
        if (_screenState.value.equals(Constants.SCREEN_STATE_ADD)) {
            saveTransaction()
        } else {
            updateTransaction()
        }
    }

    fun onNavigateToListScreenComplete() {
        _navigateToList.value = false
        _showSnackBarEvent.value = screenState.value
    }


    private fun setTransactionUIData(transactionData: TransactionData) {
        transactionType.value = transactionData.type
        amount.value = transactionData.amount
        date.value = transactionData.date
        time.value = transactionData.time
        description.value = transactionData.description
    }

    private fun saveTransaction() {
        viewModelScope.launch {
            val newTransaction = TransactionData()
            newTransaction.type = transactionType.value
            newTransaction.amount = amount.value
            newTransaction.date = date.value
            newTransaction.time = time.value
            newTransaction.description = description.value
            repository.insertTransaction(newTransaction)
            _navigateToList.value = true
        }
    }

    private fun updateTransaction() {
        viewModelScope.launch {
            val updatedTransaction = TransactionData(
                transactionData?.id,
                transactionType.value,
                amount.value,
                date.value,
                time.value,
                description.value,
            )
            repository.updateTransaction(updatedTransaction)
            _navigateToList.value = true
        }
    }

}