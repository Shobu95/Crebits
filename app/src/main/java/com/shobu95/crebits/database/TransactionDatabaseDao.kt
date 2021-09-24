package com.shobu95.crebits.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shobu95.crebits.database.entities.Transaction

@Dao
interface TransactionDatabaseDao {

    @Insert
    fun insert(transaction: Transaction)

    @Update
    fun update(transaction: Transaction)

    @Query("SELECT * FROM transactions ORDER BY id DESC")
    fun getAll(): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getById(id: Int): Transaction?

    @Delete
    fun delete(transaction: Transaction)

}