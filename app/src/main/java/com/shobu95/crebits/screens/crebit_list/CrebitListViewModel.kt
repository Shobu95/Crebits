package com.shobu95.crebits.screens.crebit_list

import androidx.lifecycle.ViewModel
import com.shobu95.crebits.database.TransactionDatabaseDao

class CrebitListViewModel(val database: TransactionDatabaseDao) : ViewModel() {

    val transactions = database.getAll()

}