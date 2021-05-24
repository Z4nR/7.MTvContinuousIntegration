package com.zulham.mtv.core.data.remote.network

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object{
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        private val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okHttp = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        @Volatile
        private var retrofit: Retrofit? = null
        @InternalCoroutinesApi
        fun getInstance(): Retrofit{
            return retrofit ?: synchronized(this) {
                retrofit ?: Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttp)
                        .build()
            }
        }
    }

}
