package com.zulham.mtv.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zulham.mtv.data.remote.RemoteDataSource
import com.zulham.mtv.data.remote.ShowDataSource
import com.zulham.mtv.data.remote.response.ResultsMovies
import com.zulham.mtv.data.remote.response.ShowResponseMovies
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ShowRepository private constructor(private val remoteDataSource: RemoteDataSource): ShowDataSource{

    companion object{
        @Volatile
        private var instance: ShowRepository? = null
        fun getInstance(remoteDataSource: RemoteDataSource): ShowRepository =
            instance ?: synchronized(this){
                instance ?: ShowRepository(remoteDataSource)
            }
    }

    override fun getMovieList(page_movie: Int): LiveData<List<ResultsMovies>> {
        val mListMovie = MutableLiveData<List<ResultsMovies>>()
        remoteDataSource.getMovieList(page_movie, object : RemoteDataSource.LoadListCallback{
            override fun onAllListReceive(resultsItem: List<ResultsMovies>) {
                mListMovie.postValue(resultsItem)
            }
        })

        return mListMovie

    }

    override fun getTVShowList(page_tv: Int): LiveData<List<ResultsMovies>> {
        val mListTV = MutableLiveData<List<ResultsMovies>>()
        remoteDataSource.getTVShowList(page_tv, object : RemoteDataSource.LoadListCallback{
            override fun onAllListReceive(resultsItem: List<ResultsMovies>) {
                mListTV.postValue(resultsItem)
            }
        })

        return mListTV

    }

    override fun getMovieDetail(id_movie: Int): LiveData<ShowResponseMovies> {
        val mMovieDetail = MutableLiveData<ShowResponseMovies>()
        remoteDataSource.getMovieDetail(id_movie, object : RemoteDataSource.LoadDetailCallback{
            override fun onDetailReceive(detailItem: ShowResponseMovies) {
                mMovieDetail.postValue(detailItem)
            }
        })

        return mMovieDetail

    }

    override fun getTVDetail(id_tv: Int): LiveData<ShowResponseMovies> {
        val mTVDetail = MutableLiveData<ShowResponseMovies>()
        remoteDataSource.getTVDetail(id_tv, object : RemoteDataSource.LoadDetailCallback{
            override fun onDetailReceive(detailItem: ShowResponseMovies) {
                mTVDetail.postValue(detailItem)
            }
        })

        return mTVDetail

    }

}