package com.shobu95.crebits.screens.add_edit_crebit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shobu95.crebits.database.TransactionDatabaseDao
import com.shobu95.crebits.database.entities.Transaction

class AddEditCrebitViewModelFactory(
    private val transaction: Transaction?,
    private val database: TransactionDatabaseDao
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditCrebitViewModel::class.java)) {
            return AddEditCrebitViewModel(transaction, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}