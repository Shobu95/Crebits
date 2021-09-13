package com.shobu95.crebits.features.add_edit_crebit

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shobu95.crebits.models.Transaction
import com.shobu95.crebits.utils.enums.TransactionType

class AddEditCrebitViewModel: ViewModel() {

    private val _transactionType = MutableLiveData<TransactionType>()
    val transactionType: LiveData<TransactionType> = _transactionType

    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    fun setTransactionType(type: TransactionType) {
        _transactionType.value = type
    }

    fun save() {
        val transaction = Transaction()
        transaction.type = _transactionType.value
        transaction.amount = amount.value
        transaction.date = date.value
        transaction.time = time.value
        transaction.description = description.value

        Log.d("Crebit", transaction.toString())
    }


}