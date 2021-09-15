package com.shobu95.crebits.features.add_edit_crebit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shobu95.crebits.database.TransactionDatabaseDao
import com.shobu95.crebits.database.entities.Transaction
import com.shobu95.crebits.utils.enums.TransactionType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddEditCrebitViewModel(val database: TransactionDatabaseDao) : ViewModel() {

    val transactionType = MutableLiveData<String>()
    val amount = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val allTransactions = database.getAll()

    fun setTransactionType(type: TransactionType) {
        transactionType.value = type.name
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
        }
    }

    private suspend fun save(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            Log.d("Crebit", transaction.toString())
            database.save(transaction)
        }
    }


}