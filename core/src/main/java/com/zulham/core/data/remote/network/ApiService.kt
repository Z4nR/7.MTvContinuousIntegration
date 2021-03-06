package com.zulham.core.data.remote.network

import com.zulham.core.BuildConfig.ApiKey
import com.zulham.core.data.remote.response.PageResponseMovies
import com.zulham.core.data.remote.response.PageResponseTV
import com.zulham.core.data.remote.response.ShowResponseMovies
import com.zulham.core.data.remote.response.ShowResponseTV
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val apiKey: String = ApiKey

interface ApiService {

    @Headers("Authorization: token $apiKey")
    @GET("discover/movie")
    suspend fun getMovieList(
            @Query("api_key") api_key: String,
            @Query("page") page: Int
    ): PageResponseMovies

    @Headers("Authorization: token $apiKey")
    @GET("discover/tv")
    suspend fun getTVShowList(
            @Query("api_key") api_key: String,
            @Query("page") page: Int
    ): PageResponseTV


    @Headers("Authorization: token $apiKey")
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
            @Path("movie_id") movie_id: Int,
            @Query("api_key") api_key: String
    ): ShowResponseMovies

    @Headers("Authorization: token $apiKey")
    @GET("tv/{tv_id}")
    suspend fun getTVShowDetail(
            @Path("tv_id") tv_id: Int,
            @Query("api_key") api_key: String
    ): ShowResponseTV

}