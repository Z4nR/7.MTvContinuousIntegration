package com.zulham.favorite.presentation.main

import androidx.lifecycle.*
import com.zulham.core.domain.model.Show
import com.zulham.core.domain.usecase.ShowUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class FavoriteModuleViewModel(private val showUseCase: ShowUseCase): ViewModel() {

    private val errorMessage = MutableLiveData<String>()

    fun favMovie(): LiveData<List<Show>>{
        val movie = showUseCase.getFavMovie().asLiveData()
        movie.observeForever {
            val error = if (it.isEmpty()) "You Don't Have a Data" else ""
            errorMessage.postValue(error)
        }
        return movie
    }

    fun favTVShow(): LiveData<List<Show>>{
        val tvShow = showUseCase.getFavTV().asLiveData()
        tvShow.observeForever {
            val error = if (it.isEmpty()) "You Don't Have a Data" else ""
            errorMessage.postValue(error)
        }
        return tvShow
    }

    fun swipeDeleteFav(dataEntity: Show){
        viewModelScope.launch{
            dataEntity.showId?.let { showUseCase.deleteFav(it) }
        }
    }

}