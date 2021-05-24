package com.zulham.mtv.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zulham.mtv.core.data.Resources
import com.zulham.mtv.core.data.local.entity.DataEntity
import com.zulham.mtv.core.data.local.entity.DetailEntity
import com.zulham.mtv.core.domain.usecase.ShowUseCase
import com.zulham.mtv.detail.DetailActivity.Companion.MOVIE
import com.zulham.mtv.detail.DetailActivity.Companion.TV_SHOW
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class DetailViewModel(private val showUseCase: ShowUseCase): ViewModel() {

    private lateinit var resultData: LiveData<Resources<DetailEntity>>

    private fun setMovie(id_movie : Int){
        resultData = showUseCase.getMovieDetail(id_movie)
    }

    private fun setTV(id_tv : Int){
        resultData = showUseCase.getTVDetail(id_tv)
    }

    fun getData(type: String?, id: Int): LiveData<Resources<DetailEntity>>{
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

    fun checkFavourite(id: Int): LiveData<Boolean>{
        return showUseCase.checkFav(id)
    }

    fun addFav(dataEntity: DataEntity){
        dataEntity.id?.let { showUseCase.setFav(it) }
    }

    fun deleteFav(dataEntity: DataEntity){
        dataEntity.id?.let { showUseCase.deleteFav(it) }
    }

}