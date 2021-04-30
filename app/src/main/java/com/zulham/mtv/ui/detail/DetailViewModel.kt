package com.zulham.mtv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zulham.mtv.data.remote.response.ShowResponseMovies
import com.zulham.mtv.data.repository.ShowRepository
import com.zulham.mtv.ui.detail.DetailActivity.Companion.MOVIE
import com.zulham.mtv.ui.detail.DetailActivity.Companion.TV_SHOW
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class DetailViewModel(private val showRepository: ShowRepository): ViewModel() {

    private lateinit var resultData: LiveData<ShowResponseMovies>

    private fun setMovie(id_movie : Int){
        resultData = showRepository.getMovieDetail(id_movie)
    }

    private fun setTV(id_tv : Int){
        resultData = showRepository.getTVDetail(id_tv)
    }

    fun getData(type: String?, id: Int): LiveData<ShowResponseMovies>{
        when (type){
            MOVIE -> setMovie(id)
            TV_SHOW -> setTV(id)
        }
        return resultData
    }

    private var showId: Int? = null

    fun setSelectedShow(showId: Int?) {
        this.showId = showId
    }

}