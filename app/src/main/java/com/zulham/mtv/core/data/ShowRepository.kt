package com.zulham.mtv.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.zulham.mtv.core.data.local.LocalDataSource
import com.zulham.mtv.core.data.local.entity.DetailEntity
import com.zulham.mtv.core.data.local.entity.GenresItemMovies
import com.zulham.mtv.core.data.local.entity.ProductionCompaniesItemMovies
import com.zulham.mtv.core.data.remote.RemoteDataSource
import com.zulham.mtv.core.data.remote.network.ApiResponse
import com.zulham.mtv.core.data.remote.response.ResultsMovies
import com.zulham.mtv.core.data.remote.response.ShowResponseMovies
import com.zulham.mtv.core.domain.model.Show
import com.zulham.mtv.core.domain.repository.IShowRepository
import com.zulham.mtv.core.utils.AppExecutors
import com.zulham.mtv.core.utils.DataMapper
import com.zulham.mtv.core.utils.ShowType.MOVIE_TYPE
import com.zulham.mtv.core.utils.ShowType.TV_TYPE
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ShowRepository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors):
    IShowRepository {

    companion object{
        @Volatile
        private var instance: ShowRepository? = null
        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): ShowRepository =
            instance ?: synchronized(this){
                instance ?: ShowRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getMovieList(page_movie: Int): LiveData<Resources<List<Show>>> {
        return object : NetworkResource<List<Show>, List<ResultsMovies>>(appExecutors){

            override fun loadFromDB(): LiveData<List<Show>> {
                return Transformations.map(localDataSource.getMovieData()) {
                    DataMapper.mapDataToShow(it)
                }
            }

            override fun shouldFetch(data: List<Show>?): Boolean =
                data == null || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovies>>> {
                val mListMovie = MutableLiveData<ApiResponse<List<ResultsMovies>>>()
                remoteDataSource.getMovieList(
                    page_movie,
                    object : RemoteDataSource.LoadListCallback {
                        override fun onAllListReceive(resultsItem: ApiResponse<List<ResultsMovies>>) {
                            mListMovie.postValue(resultsItem)
                        }
                    })

                return mListMovie
            }

            override fun saveCallResult(data: List<ResultsMovies>) {
                val movieList = DataMapper.mapPageToData(data)
                localDataSource.insertShowData(movieList)
            }

        }.asLiveData()

    }

    override fun getTVShowList(page_tv: Int): LiveData<Resources<List<Show>>> {
        return object : NetworkResource<List<Show>, List<ResultsMovies>>(appExecutors){
            override fun loadFromDB(): LiveData<List<Show>> {
                return Transformations.map(localDataSource.getTVsData()) {
                    DataMapper.mapDataToShow(it)
                }
            }

            override fun shouldFetch(data: List<Show>?): Boolean =
                data == null || data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovies>>> {
                val mListTV = MutableLiveData<ApiResponse<List<ResultsMovies>>>()
                remoteDataSource.getTVShowList(
                    page_tv,
                    object : RemoteDataSource.LoadListCallback {
                        override fun onAllListReceive(resultsItem: ApiResponse<List<ResultsMovies>>) {
                            mListTV.postValue(resultsItem)
                        }

                    })

                return mListTV
            }

            override fun saveCallResult(data: List<ResultsMovies>) {
                val tvList = DataMapper.mapPageToData(data)
                localDataSource.insertShowData(tvList)
            }

        }.asLiveData()

    }

    override fun getMovieDetail(id_movie: Int): LiveData<Resources<DetailEntity>> {
        return object : NetworkResource<DetailEntity, ShowResponseMovies>(appExecutors){
            override fun loadFromDB(): LiveData<DetailEntity> = localDataSource.getDetailMovie(id_movie)

            override fun shouldFetch(data: DetailEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<ShowResponseMovies>> {
                val movieDetail = MutableLiveData<ApiResponse<ShowResponseMovies>>()
                remoteDataSource.getMovieDetail(
                    id_movie,
                    object : RemoteDataSource.LoadDetailCallback {
                        override fun onDetailReceive(detailItem: ApiResponse<ShowResponseMovies>) {
                            movieDetail.postValue(detailItem)
                        }

                    }
                )
                return movieDetail
            }

            override fun saveCallResult(data: ShowResponseMovies) {
                val genres = data.genres?.map { it -> GenresItemMovies(it.name) }
                val production = data.productionCompanies?.map { it -> ProductionCompaniesItemMovies(it.name) }
                val detail = DetailEntity(
                    data.backdropPath,
                    data.overview,
                    production,
                    data.releaseDate,
                    genres,
                    data.id,
                    data.title,
                    data.posterPath,
                    false,
                    MOVIE_TYPE
                )
                localDataSource.insertShowDetail(detail)
            }

        }.asLiveData()

    }

    override fun getTVDetail(id_tv: Int): LiveData<Resources<DetailEntity>> {
        return object : NetworkResource<DetailEntity, ShowResponseMovies>(appExecutors){
            override fun loadFromDB(): LiveData<DetailEntity> = localDataSource.getDetailTVShow(id_tv)

            override fun shouldFetch(data: DetailEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<ShowResponseMovies>> {
                val tvDetail = MutableLiveData<ApiResponse<ShowResponseMovies>>()
                remoteDataSource.getTVDetail(
                    id_tv,
                    object : RemoteDataSource.LoadDetailCallback {
                        override fun onDetailReceive(detailItem: ApiResponse<ShowResponseMovies>) {
                            tvDetail.postValue(detailItem)
                        }

                    }
                )
                return tvDetail
            }

            override fun saveCallResult(data: ShowResponseMovies) {
                val genres = data.genres?.map { it -> GenresItemMovies(it.name) }
                val production = data.productionCompanies?.map { it -> ProductionCompaniesItemMovies(it.name) }
                val detail = DetailEntity(
                    data.backdropPath,
                    data.overview,
                    production,
                    data.releaseDate,
                    genres,
                    data.id,
                    data.title,
                    data.posterPath,
                    false,
                    TV_TYPE
                )
                localDataSource.insertShowDetail(detail)
            }

        }.asLiveData()
    }

    override fun getFavMovie(): LiveData<List<Show>> {
        return Transformations.map(localDataSource.getFavMovie()) {
            DataMapper.mapDataToShow(it)
        }
    }

    override fun getFavTV(): LiveData<List<Show>> {
        return Transformations.map(localDataSource.getFavTV()) {
            DataMapper.mapDataToShow(it)
        }
    }

    override fun checkFav(id: Int): LiveData<Boolean> {
        return localDataSource.checkFav(id)
    }

    override fun setFav(id: Int) {
        appExecutors.diskIO().execute{
            localDataSource.setFavourite(id)
        }
    }

    override fun deleteFav(id: Int) {
        appExecutors.diskIO().execute {
            localDataSource.deleteFav(id)
        }
    }

}