package com.zulham.mtv.data.remote

import com.zulham.mtv.BuildConfig
import com.zulham.mtv.api.ServiceApi
import com.zulham.mtv.data.remote.response.*
import com.zulham.mtv.utils.DummyData
import com.zulham.mtv.utils.IdlingResource
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val serviceApi: ServiceApi){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        @InternalCoroutinesApi
        fun getInstance(helper: ServiceApi): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getMovieList(pageMovie: Int, callback: LoadListCallback) {
        IdlingResource.idlingIncrement()
        serviceApi.getMovieList(BuildConfig.ApiKey, pageMovie).enqueue(object : Callback<PageResponseMovies> {
            override fun onResponse(call: Call<PageResponseMovies>,
                                    response: Response<PageResponseMovies>
            ) {
                if (response.isSuccessful){
                    val movies = response.body()?.results
                    if (movies != null) {
                        callback.onAllListReceive(movies)
                    }
                    IdlingResource.idlingDecrement()
                }
            }

            override fun onFailure(call: Call<PageResponseMovies>, t: Throwable) {
                val dummyMovies = DummyData.generateDummyMovie()
                val resultMovies = arrayListOf<ResultsMovies>()
                dummyMovies.forEach{ dummy ->
                    val entity = ResultsMovies(dummy.description, dummy.title, dummy.img, dummy.backdrop, dummy.releaseDate, dummy.showId)
                    resultMovies.add(entity)
                }
                callback.onAllListReceive(resultMovies.toList())
                IdlingResource.idlingDecrement()
            }

        })

    }


    fun getTVShowList(pageTV: Int, callback: LoadListCallback) {
        IdlingResource.idlingIncrement()
        serviceApi.getTVShowList(BuildConfig.ApiKey, pageTV).enqueue(object : Callback<PageResponseTV>{
            override fun onResponse(call: Call<PageResponseTV>,
                                    response: Response<PageResponseTV>) {
                val resultMovies = arrayListOf<ResultsMovies>()
                if (response.isSuccessful){
                    val tvs = response.body()?.results
                    tvs?.forEach { movie ->
                        val entity = ResultsMovies(movie.overview, movie.name, movie.posterPath, movie.backdropPath, movie.firstAirDate, movie.id)
                        resultMovies.add(entity)
                    }
                }

                callback.onAllListReceive(resultMovies.toList())
                IdlingResource.idlingDecrement()

            }

            override fun onFailure(call: Call<PageResponseTV>, t: Throwable) {
                val dummyTV = DummyData.generateDummyTV()
                val resultOffline = arrayListOf<ResultsMovies>()
                dummyTV.forEach{ dummy ->
                    val entity = ResultsMovies(dummy.description, dummy.title, dummy.img, dummy.backdrop, dummy.releaseDate, dummy.showId)
                    resultOffline.add(entity)
                }
                callback.onAllListReceive(resultOffline.toList())
                IdlingResource.idlingDecrement()
            }

        })
    }


    fun getMovieDetail(idMovie: Int, callback: LoadDetailCallback) {
        IdlingResource.idlingIncrement()
        serviceApi.getMovieDetail(idMovie, BuildConfig.ApiKey).enqueue(object : Callback<ShowResponseMovies>{
            override fun onResponse(call: Call<ShowResponseMovies>,
                                    response: Response<ShowResponseMovies>) {
                if (response.isSuccessful){
                    val movies = response.body()
                    callback.onDetailReceive(movies!!)
                    IdlingResource.idlingDecrement()
                }
            }

            override fun onFailure(call: Call<ShowResponseMovies>, t: Throwable) {
                val dummyMovies = DummyData.generateDummyMovie()
                val resultMovies = dummyMovies.find { it.showId == idMovie }
                val production = resultMovies?.production?.map { it ->
                    ProductionCompaniesItemMovies(it.name)
                }
                val genre = resultMovies?.genre?.map { it ->
                    GenresItemMovies(it.name)
                }
                val movies = ShowResponseMovies(
                        resultMovies?.backdrop,
                        resultMovies?.description,
                        production,
                        resultMovies?.releaseDate,
                        genre,
                        resultMovies?.showId,
                        resultMovies?.title,
                        resultMovies?.img)
                callback.onDetailReceive(movies)
                IdlingResource.idlingDecrement()
            }

        })
    }

    fun getTVDetail(idTV: Int, callback: LoadDetailCallback) {
        IdlingResource.idlingIncrement()
        serviceApi.getTVShowDetail(idTV, BuildConfig.ApiKey).enqueue(object : Callback<ShowResponseTV>{
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
                    callback.onDetailReceive(results)
                    IdlingResource.idlingDecrement()
                }
            }

            override fun onFailure(call: Call<ShowResponseTV>, t: Throwable) {
                val dummyTV = DummyData.generateDummyTV()
                val resultTV = dummyTV.find { it.showId == idTV }
                val production = resultTV?.production?.map { it ->
                    ProductionCompaniesItemMovies(it.name)
                }
                val genre = resultTV?.genre?.map { it ->
                    GenresItemMovies(it.name)
                }
                val tvs = ShowResponseMovies(
                        resultTV?.backdrop,
                        resultTV?.description,
                        production,
                        resultTV?.releaseDate,
                        genre,
                        resultTV?.showId,
                        resultTV?.title,
                        resultTV?.img
                )

                callback.onDetailReceive(tvs)
                IdlingResource.idlingDecrement()

            }

        })
    }

    interface LoadListCallback {
        fun onAllListReceive(resultsItem: List<ResultsMovies>)
    }

    interface LoadDetailCallback {
        fun onDetailReceive(detailItem: ShowResponseMovies)
    }

}