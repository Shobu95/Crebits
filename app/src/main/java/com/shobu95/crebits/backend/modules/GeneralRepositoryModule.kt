package com.shobu95.crebits.backend.modules

import com.shobu95.crebits.backend.local.TransactionDatabaseDao
import com.shobu95.crebits.backend.repository.GeneralDataSource
import com.shobu95.crebits.backend.repository.GeneralRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author AliAzazAlam on 10/29/2021.
 */
@Module
class GeneralRepositoryModule {

    @Singleton
    @Provides
    fun provideGeneralDataSource(transactionDatabaseDao: TransactionDatabaseDao): GeneralDataSource {
        return GeneralRepository(transactionDatabaseDao)
    }

}