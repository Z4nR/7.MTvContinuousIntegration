package com.zulham.core.data

import androidx.lifecycle.LiveData
import com.zulham.core.data.local.LocalDataSource
import com.zulham.core.data.local.entity.DetailEntity
import com.zulham.core.data.local.entity.GenresItem
import com.zulham.core.data.local.entity.ProductionCompaniesItem
import com.zulham.core.data.remote.RemoteDataSource
import com.zulham.core.data.remote.network.ApiResponse
import com.zulham.core.data.remote.response.ResultsMovies
import com.zulham.core.data.remote.response.ResultsTV
import com.zulham.core.data.remote.response.ShowResponseMovies
import com.zulham.core.data.remote.response.ShowResponseTV
import com.zulham.core.domain.model.Show
import com.zulham.core.domain.repository.IShowRepository
import com.zulham.core.utils.DataMapper
import com.zulham.core.utils.ShowType.MOVIE_TYPE
import com.zulham.core.utils.ShowType.TV_TYPE
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@InternalCoroutinesApi
class ShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IShowRepository {

    override fun getMovieList(page_movie: Int): Flow<Resources<List<Show>>> {
        return object : com.zulham.core.data.NetworkResource<List<Show>, List<ResultsMovies>>(){

            override fun loadFromDB(): Flow<List<Show>> {
                return localDataSource.getMovieData().map {
                    DataMapper.mapDataToShow(it)
                }
            }

            override fun shouldFetch(data: List<Show>?): Boolean =
                data == null || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsMovies>>> =
                remoteDataSource.getMovieList(page_movie)

            override suspend fun saveCallResult(data: List<ResultsMovies>) {
                val movieList = DataMapper.mapPageToDataMovie(data)
                localDataSource.insertShowData(movieList)
            }

        }.asFlow()

    }

    override fun getTVShowList(page_tv: Int): Flow<Resources<List<Show>>> {
        return object : com.zulham.core.data.NetworkResource<List<Show>, List<ResultsTV>>(){
            override fun loadFromDB(): Flow<List<Show>> {
                return localDataSource.getTVsData().map {
                    DataMapper.mapDataToShow(it)
                }
            }

            override fun shouldFetch(data: List<Show>?): Boolean =
                data == null || data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsTV>>> =
                remoteDataSource.getTVShowList(page_tv)

            override suspend fun saveCallResult(data: List<ResultsTV>) {
                val tvList = DataMapper.mapPageToDataTV(data)
                localDataSource.insertShowData(tvList)
            }

        }.asFlow()

    }

    override fun getMovieDetail(id_movie: Int): Flow<Resources<DetailEntity>> {
        return object : com.zulham.core.data.NetworkResource<DetailEntity, ShowResponseMovies>(){
            override fun loadFromDB(): Flow<DetailEntity> = localDataSource.getDetailMovie(id_movie)

            override fun shouldFetch(data: DetailEntity?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<ShowResponseMovies>> =
                remoteDataSource.getMovieDetail(id_movie)

            override suspend fun saveCallResult(data: ShowResponseMovies) {
                val genres = data.genres?.map { it -> GenresItem(it.name) }
                val production = data.productionCompanies?.map { it -> ProductionCompaniesItem(it.name) }
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

        }.asFlow()

    }

    override fun getTVDetail(id_tv: Int): Flow<Resources<DetailEntity>> {
        return object : com.zulham.core.data.NetworkResource<DetailEntity, ShowResponseTV>(){
            override fun loadFromDB(): Flow<DetailEntity> = localDataSource.getDetailTVShow(id_tv)

            override fun shouldFetch(data: DetailEntity?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<ShowResponseTV>> =
                remoteDataSource.getTVDetail(id_tv)

            override suspend fun saveCallResult(data: ShowResponseTV) {
                val genres = data.genres?.map { it -> GenresItem(it.name) }
                val production = data.productionCompanies?.map { it -> ProductionCompaniesItem(it.name) }
                val detail = DetailEntity(
                    data.backdropPath,
                    data.overview,
                    production,
                    data.firstAirDate,
                    genres,
                    data.id,
                    data.title,
                    data.posterPath,
                    false,
                    TV_TYPE
                )
                localDataSource.insertShowDetail(detail)
            }

        }.asFlow()
    }

    override fun getFavMovie(): Flow<List<Show>> {
        return localDataSource.getFavMovie().map {
            DataMapper.mapDataToShow(it)
        }
    }

    override fun getFavTV(): Flow<List<Show>> {
        return localDataSource.getFavTV().map {
            DataMapper.mapDataToShow(it)
        }
    }

    override fun checkFav(id: Int): LiveData<Boolean> {
        return localDataSource.checkFav(id)
    }

    override suspend fun setFav(id: Int) {
        localDataSource.setFavourite(id)
    }

    override suspend fun deleteFav(id: Int) {
        localDataSource.deleteFav(id)
    }

}