package com.shobu95.crebits.screens.crebit_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shobu95.crebits.database.TransactionDatabaseDao

class CrebitListViewModelFactory(private val database: TransactionDatabaseDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrebitListViewModel::class.java)) {
            return CrebitListViewModel(database) as T
        }
        throw IllegalArgumentException("Unknow Viewmodel class")
    }
}