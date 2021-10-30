package com.shobu95.crebits.backend.repository

import com.shobu95.crebits.model.TransactionData
import kotlinx.coroutines.flow.Flow

/**
 * @author AliAzazAlam on 10/29/2021.
 */
interface GeneralDataSource {

    /*
    * Get all TransactionData
    * */
    suspend fun getAllTransaction(): Flow<List<TransactionData>>

    suspend fun getTransactionByID(id: Int): TransactionData?

    suspend fun insertTransaction(transaction: TransactionData): Long

    suspend fun updateTransaction(transaction: TransactionData): Int

    suspend fun deleteTransaction(transaction: TransactionData)
    /*
    * Get all TransactionData End
    * */

}