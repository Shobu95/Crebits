package com.shobu95.crebits.backend.repository

import com.shobu95.crebits.backend.local.TransactionDatabaseDao
import com.shobu95.crebits.model.TransactionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author AliAzazAlam on 10/29/2021.
 */
class GeneralRepository @Inject constructor(
    private val transactionDAO: TransactionDatabaseDao
) : GeneralDataSource {

    override suspend fun getAllTransaction(): Flow<List<TransactionData>> {
        return transactionDAO.getAll()
    }

    override suspend fun insertTransaction(transaction: TransactionData): Long =
        withContext(Dispatchers.IO) {
            transactionDAO.insert(transaction)
        }

    override suspend fun updateTransaction(transaction: TransactionData): Int =
        withContext(Dispatchers.IO) {
            transactionDAO.update(transaction)
        }

    override suspend fun getTransactionByID(id:Int): TransactionData? =
        withContext(Dispatchers.IO) {
            transactionDAO.getById(id)
        }

    override suspend fun deleteTransaction(transaction: TransactionData) =
        withContext(Dispatchers.IO) {
            transactionDAO.delete(transaction)
        }
}