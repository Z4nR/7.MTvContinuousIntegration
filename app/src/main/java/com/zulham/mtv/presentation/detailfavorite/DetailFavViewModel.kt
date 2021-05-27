package com.zulham.mtv.presentation.detailfavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zulham.core.data.Resources
import com.zulham.core.data.local.entity.DetailEntity
import com.zulham.core.domain.usecase.ShowUseCase
import com.zulham.mtv.presentation.detailfavorite.DetailFavoriteActivity.Companion.MOVIE
import com.zulham.mtv.presentation.detailfavorite.DetailFavoriteActivity.Companion.TV_SHOW
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class DetailFavViewModel(private val showUseCase: ShowUseCase): ViewModel() {

    private lateinit var resultData: LiveData<Resources<DetailEntity>>

    private fun setMovie(id_movie : Int){
        resultData = showUseCase.getMovieDetail(id_movie).asLiveData()
    }

    private fun setTV(id_tv : Int){
        resultData = showUseCase.getTVDetail(id_tv).asLiveData()
    }

    fun getData(type: String?, id: Int): LiveData<Resources<DetailEntity>> {
        when (type){
            MOVIE -> setMovie(id)
            TV_SHOW -> setTV(id)
        }
        return resultData
    }

}