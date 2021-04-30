package com.zulham.mtv.data.remote

import androidx.lifecycle.LiveData
import com.zulham.mtv.data.remote.response.ResultsMovies
import com.zulham.mtv.data.remote.response.ShowResponseMovies

interface ShowDataSource {

    fun getMovieList(page_movie: Int): LiveData<List<ResultsMovies>>

    fun getTVShowList(page_tv: Int): LiveData<List<ResultsMovies>>

    fun getMovieDetail(id_movie: Int): LiveData<ShowResponseMovies>

    fun getTVDetail(id_tv: Int): LiveData<ShowResponseMovies>

}