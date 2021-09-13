package com.shobu95.crebits.models

import com.shobu95.crebits.utils.enums.TransactionType

data class Transaction(


    var id: Int? = null,
    var type: TransactionType? = null,
    var amount: String? = null,
    var date: String? = null,
    var time: String? = null,
    var description: String? = null

) {
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