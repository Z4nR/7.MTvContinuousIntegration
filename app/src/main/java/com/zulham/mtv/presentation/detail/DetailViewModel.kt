package com.zulham.mtv.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zulham.core.data.Resources
import com.zulham.core.data.local.entity.DataEntity
import com.zulham.core.data.local.entity.DetailEntity
import com.zulham.core.domain.usecase.ShowUseCase
import com.zulham.mtv.presentation.detail.DetailActivity.Companion.MOVIE
import com.zulham.mtv.presentation.detail.DetailActivity.Companion.TV_SHOW
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class DetailViewModel(private val showUseCase: ShowUseCase): ViewModel() {

    private lateinit var resultData: LiveData<Resources<DetailEntity>>

    private fun setMovie(id_movie : Int){
        resultData = showUseCase.getMovieDetail(id_movie).asLiveData()
    }

    private fun setTV(id_tv : Int){
        resultData = showUseCase.getTVDetail(id_tv).asLiveData()
    }

    fun getData(type: String?, id: Int): LiveData<Resources<DetailEntity>>{
        when (type){
            MOVIE -> setMovie(id)
            TV_SHOW -> setTV(id)
        }
        return resultData
    }

    fun checkFavourite(id: Int): LiveData<Boolean>{
        return showUseCase.checkFav(id)
    }

    fun addFav(dataEntity: DataEntity){
        viewModelScope.launch{
            dataEntity.id?.let { showUseCase.setFav(it) }
        }
    }

    fun deleteFav(dataEntity: DataEntity){
        viewModelScope.launch{
            dataEntity.id?.let { showUseCase.deleteFav(it) }
        }
    }

}