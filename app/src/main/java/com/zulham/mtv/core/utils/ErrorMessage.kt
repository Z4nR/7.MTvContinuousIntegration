package com.zulham.mtv.core.utils

import retrofit2.HttpException
import java.net.UnknownHostException

object ErrorMessage {
    fun generateErrorMessage(error: Throwable): String{
        return when (error){
            is UnknownHostException -> "Network Error Please Try Again"
            is HttpException -> "HTTP is in Trouble"
            else -> error.message.toString()
        }
    }
}