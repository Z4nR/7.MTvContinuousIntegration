package com.zulham.mtv.vo

data class Resources<T>(val status: Status, val data: T?, val message: String?){

    companion object {

        fun <T> success(data: T?): Resources<T> =
            Resources(Status.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): Resources<T> =
            Resources(Status.ERROR, data, msg)

        fun <T> loading(data: T?): Resources<T> =
            Resources(Status.LOADING, data, null)

    }

}
