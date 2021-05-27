package com.zulham.core.data

sealed class Resources<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : com.zulham.core.data.Resources<T>(data)
    class Loading<T>(data: T? = null) : com.zulham.core.data.Resources<T>(data)
    class Error<T>(message: String, data: T? = null) : com.zulham.core.data.Resources<T>(data, message)
}
