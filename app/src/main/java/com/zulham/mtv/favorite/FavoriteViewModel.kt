package com.zulham.mtv.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zulham.mtv.core.domain.model.Show
import com.zulham.mtv.core.domain.usecase.ShowUseCase
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoriteViewModel(private val showUseCase: ShowUseCase): ViewModel() {

    private val errorMessage = MutableLiveData<String>()

    fun favMovie(): LiveData<List<Show>>{
        val movie = showUseCase.getFavMovie()
        movie.observeForever {
            val error = if (it.isEmpty()) "You Don't Have a Data" else ""
            errorMessage.postValue(error)
        }
        return movie
    }

    fun favTVShow(): LiveData<List<Show>>{
        val tvShow = showUseCase.getFavTV()
        tvShow.observeForever {
            val error = if (it.isEmpty()) "You Don't Have a Data" else ""
            errorMessage.postValue(error)
        }
        return tvShow
    }

    fun swipeDeleteFav(dataEntity: Show){
        dataEntity.showId?.let { showUseCase.deleteFav(it) }
    }

}