package com.shobu95.crebits.backend.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shobu95.crebits.model.TransactionData
import com.shobu95.crebits.utils.Constants.DATABASE_NAME
import com.shobu95.crebits.utils.Constants.DATABASE_VERSION

/**
 * @author AliAzazAlam on 10/29/2021.
 */
@Database(
    entities = [TransactionData::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun vehicleDao(): TransactionDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: TransactionDatabase? = null

        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TransactionDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

    }
}