package com.shobu95.crebits.backend.modules

import android.content.Context
import com.shobu95.crebits.backend.local.TransactionDatabase
import com.shobu95.crebits.backend.local.TransactionDatabaseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author AliAzazAlam on 10/29/2021.
 */
@Module
class DatabaseModule {

    @Provides
    fun provideTransactionAppDatabase(context: Context): TransactionDatabase {
        return TransactionDatabase.invoke(context)
    }

    @Singleton
    @Provides
    fun getDAO(transactionDatabase: TransactionDatabase): TransactionDatabaseDao {
        return transactionDatabase.vehicleDao()
    }

}