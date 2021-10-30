package com.shobu95.crebits.backend.local

import androidx.room.*
import com.shobu95.crebits.model.TransactionData
import com.shobu95.crebits.utils.Constants
import kotlinx.coroutines.flow.Flow

/**
 * @author AliAzazAlam on 10/29/2021.
 */
@Dao
interface TransactionDatabaseDao {

    /*
    * Transaction
    * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(transactionData: TransactionData): Long

    @Update
    fun update(transactionData: TransactionData): Int

    @Query("SELECT * FROM ${Constants.TRANSACTION_TABLE} ORDER BY id DESC")
    fun getAll(): Flow<List<TransactionData>>

    @Query("SELECT * FROM ${Constants.TRANSACTION_TABLE} WHERE id = :id")
    fun getById(id: Int): TransactionData?

    @Delete
    fun delete(transactionData: TransactionData)

}