package com.zulham.mtv.core.data.remote.network

import com.zulham.mtv.BuildConfig
import com.zulham.mtv.core.data.remote.response.PageResponseMovies
import com.zulham.mtv.core.data.remote.response.PageResponseTV
import com.zulham.mtv.core.data.remote.response.ShowResponseMovies
import com.zulham.mtv.core.data.remote.response.ShowResponseTV
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val apiKey: String = BuildConfig.ApiKey

interface ApiService {

    @Headers("Authorization: token $apiKey")
    @GET("discover/movie")
    fun getMovieList(
            @Query("api_key") api_key: String,
            @Query("page") page: Int
    ): Call<PageResponseMovies>

    @Headers("Authorization: token $apiKey")
    @GET("discover/tv")
    fun getTVShowList(
            @Query("api_key") api_key: String,
            @Query("page") page: Int
    ): Call<PageResponseTV>


    @Headers("Authorization: token $apiKey")
    @GET("movie/{movie_id}")
    fun getMovieDetail(
            @Path("movie_id") movie_id: Int,
            @Query("api_key") api_key: String
    ): Call<ShowResponseMovies>

    @Headers("Authorization: token $apiKey")
    @GET("tv/{tv_id}")
    fun getTVShowDetail(
            @Path("tv_id") tv_id: Int,
            @Query("api_key") api_key: String
    ): Call<ShowResponseTV>

}