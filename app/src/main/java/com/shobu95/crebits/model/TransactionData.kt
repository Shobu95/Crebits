package com.shobu95.crebits.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shobu95.crebits.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constants.TRANSACTION_TABLE)
data class TransactionData(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    var type: String? = null,
    var amount: String? = null,
    var date: String? = null,
    var time: String? = null,
    var description: String? = null

) : Parcelable {
    override fun toString(): String {
        return "Transaction(" +
                "id=$id, " +
                "type=$type, " +
                "amount=$amount, " +
                "date=$date, " +
                "time=$time, " +
                "description=$description)"
    }
}