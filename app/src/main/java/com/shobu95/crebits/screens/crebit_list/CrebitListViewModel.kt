package com.shobu95.crebits.screens.crebit_list

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu95.crebits.Crebits
import com.shobu95.crebits.R
import com.shobu95.crebits.backend.repository.GeneralDataSource
import com.shobu95.crebits.backend.repository.ResultCallBack
import com.shobu95.crebits.model.TransactionData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import javax.inject.Inject

class CrebitListViewModel @Inject constructor(
    private val repository: GeneralDataSource
) : ViewModel() {

    private val _allTransaction: MutableLiveData<ResultCallBack<List<TransactionData>>> =
        MutableLiveData()
    val allTransaction: MutableLiveData<ResultCallBack<List<TransactionData>>>
        get() = _allTransaction

    val showDeleteSnackBar = MutableLiveData<Boolean>().apply { value = false }

    init {
        getAllTransactionInDB()
    }

    fun deleteTransaction(transactionData: TransactionData) {
        viewModelScope.launch {
            val result = async { repository.deleteTransaction(transactionData) }
            result.await()
            showDeleteSnackBar.value = true
        }
    }

    private fun getAllTransactionInDB() {

        viewModelScope.launch {
            val data = repository.getAllTransaction()
            data.collect {
                try {
                    _allTransaction.value = ResultCallBack.Success(it)
                } catch (ex: Exception) {
                    _allTransaction.value =
                        ResultCallBack.CallException(ex)
                }

            }

        }

    }

}