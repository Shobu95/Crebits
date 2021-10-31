package com.shobu95.crebits.utils

object Constants {

    const val SCREEN_STATE_EDIT = "screen_state_edit"
    const val SCREEN_STATE_ADD = "screen_state_add"
    const val TIME_FORMAT_H_MM_A = "h:mm:a"
    const val SAVE = "Save"
    const val UPDATE = "Update"
    const val DATE_PICKER = "datePicker"
    const val TIME_PICKER = "timePicker"
    const val DELETE_DIALOG = "deleteDialog"

    /*
    * Database
    * */
    const val DATABASE_NAME = "transaction_database"
    const val DATABASE_VERSION = 1
    const val TRANSACTION_TABLE = "transactions"


    enum class TransactionType {
        CREDIT, DEBIT
    }

}