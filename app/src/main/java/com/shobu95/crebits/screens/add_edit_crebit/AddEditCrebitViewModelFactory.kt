package com.shobu95.crebits.screens.add_edit_crebit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shobu95.crebits.database.TransactionDatabaseDao

class AddEditCrebitViewModelFactory(private val database: TransactionDatabaseDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditCrebitViewModel::class.java)) {
            return AddEditCrebitViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}