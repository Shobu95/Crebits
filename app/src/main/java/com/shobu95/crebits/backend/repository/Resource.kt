package com.shobu95.crebits.backend.repository

/**
 * @author AliAzazAlam on 10/29/2021.
 */
sealed class ResultCallBack<out T> {
    data class Success<out T>(val data: T) : ResultCallBack<T>()
    data class Error<out T>(val error: String) : ResultCallBack<T>()
    data class CallException<out T>(val exception: Exception) : ResultCallBack<T>()
}

