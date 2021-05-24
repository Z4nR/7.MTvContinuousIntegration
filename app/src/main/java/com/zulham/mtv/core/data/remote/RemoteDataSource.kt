package com.zulham.mtv.core.data.remote

import com.zulham.mtv.BuildConfig
import com.zulham.mtv.core.api.ApiService
import com.zulham.mtv.core.data.remote.network.ApiResponse
import com.zulham.mtv.core.data.remote.response.*
import com.zulham.mtv.core.utils.ErrorMessage
import com.zulham.mtv.core.utils.IdlingResource
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        @InternalCoroutinesApi
        fun getInstance(helper: ApiService): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getMovieList(pageMovie: Int, callback: LoadListCallback) {
        IdlingResource.idlingIncrement()
        apiService.getMovieList(BuildConfig.ApiKey, pageMovie).enqueue(object : Callback<PageResponseMovies> {
            override fun onResponse(call: Call<PageResponseMovies>,
                                    response: Response<PageResponseMovies>
            ) {
                if (response.isSuccessful){
                    val movies = response.body()?.results
                    if (movies != null) {
                        callback.onAllListReceive(ApiResponse.success(movies))
                    }
                    IdlingResource.idlingDecrement()
                } else {
                    callback.onAllListReceive(ApiResponse.empty("Sorry, You Don't Have a Data", mutableListOf()))
                }
            }

            override fun onFailure(call: Call<PageResponseMovies>, t: Throwable) {
                callback.onAllListReceive(ApiResponse.error(ErrorMessage.generateErrorMessage(t), mutableListOf()))
                IdlingResource.idlingDecrement()
            }

        })

    }


    fun getTVShowList(pageTV: Int, callback: LoadListCallback) {
        IdlingResource.idlingIncrement()
        apiService.getTVShowList(BuildConfig.ApiKey, pageTV).enqueue(object : Callback<PageResponseTV>{
            override fun onResponse(call: Call<PageResponseTV>,
                                    response: Response<PageResponseTV>) {
                val resultMovies = arrayListOf<ResultsMovies>()
                if (response.isSuccessful){
                    val tvs = response.body()?.results
                    tvs?.forEach { movie ->
                        val entity = ResultsMovies(movie.overview, movie.name, movie.posterPath, movie.backdropPath, movie.firstAirDate, movie.id)
                        resultMovies.add(entity)
                    }
                    callback.onAllListReceive(ApiResponse.success(resultMovies.toList()))
                } else {
                    callback.onAllListReceive(ApiResponse.empty("Sorry, You Don't Have a Data", mutableListOf()))
                }

                IdlingResource.idlingDecrement()

            }

            override fun onFailure(call: Call<PageResponseTV>, t: Throwable) {
                callback.onAllListReceive(ApiResponse.error(ErrorMessage.generateErrorMessage(t), mutableListOf()))
                IdlingResource.idlingDecrement()
            }

        })
    }


    fun getMovieDetail(idMovie: Int, callback: LoadDetailCallback) {
        IdlingResource.idlingIncrement()
        apiService.getMovieDetail(idMovie, BuildConfig.ApiKey).enqueue(object : Callback<ShowResponseMovies>{
            override fun onResponse(call: Call<ShowResponseMovies>,
                                    response: Response<ShowResponseMovies>) {
                if (response.isSuccessful){
                    val movies = response.body()
                    callback.onDetailReceive(ApiResponse.success(movies!!))
                } else {
                    callback.onDetailReceive(ApiResponse.empty("Sorry, You Don't Have a Data", ShowResponseMovies()))
                }
                IdlingResource.idlingDecrement()
            }

            override fun onFailure(call: Call<ShowResponseMovies>, t: Throwable) {
                callback.onDetailReceive(ApiResponse.error(ErrorMessage.generateErrorMessage(t), ShowResponseMovies()))
                IdlingResource.idlingDecrement()
            }

        })
    }

    fun getTVDetail(idTV: Int, callback: LoadDetailCallback) {
        IdlingResource.idlingIncrement()
        apiService.getTVShowDetail(idTV, BuildConfig.ApiKey).enqueue(object : Callback<ShowResponseTV>{
            override fun onResponse(call: Call<ShowResponseTV>,
                                    response: Response<ShowResponseTV>) {
                if (response.isSuccessful){
                    val tvs = response.body()
                    val productionCompaniesTV = tvs?.productionCompanies?.map{ it ->
                        ProductionCompaniesItemMovies(it.name)}
                    val genresItemTV = tvs?.genres?.map{ it ->
                        GenresItemMovies(it.name)}
                    val results = ShowResponseMovies(
                            tvs?.backdropPath,
                            tvs?.overview,
                            productionCompaniesTV,
                            tvs?.firstAirDate,
                            genresItemTV,
                            tvs?.id,
                            tvs?.title,
                            tvs?.posterPath)
                    callback.onDetailReceive(ApiResponse.success(results))

                } else {
                    callback.onDetailReceive(ApiResponse.empty("Sorry, You Don't Have a Data", ShowResponseMovies()))
                }
                IdlingResource.idlingDecrement()
            }

            override fun onFailure(call: Call<ShowResponseTV>, t: Throwable) {
                callback.onDetailReceive(ApiResponse.error(ErrorMessage.generateErrorMessage(t), ShowResponseMovies()))
                IdlingResource.idlingDecrement()

            }

        })
    }

    interface LoadListCallback {
        fun onAllListReceive(resultsItem: ApiResponse<List<ResultsMovies>>)
    }

    interface LoadDetailCallback {
        fun onDetailReceive(detailItem: ApiResponse<ShowResponseMovies>)
    }

}