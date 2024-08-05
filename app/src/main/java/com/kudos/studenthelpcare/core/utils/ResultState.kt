package com.kudos.studenthelpcare.core.utils

sealed class ResultState<out T: Any?>{
    object Loading : ResultState<Nothing>()
    data class Success<out T: Any?>(val data: T) : ResultState<T>()
    data class Fail(val error: Throwable?) : ResultState<Nothing>()
}