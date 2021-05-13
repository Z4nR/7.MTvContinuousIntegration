package com.zulham.mtv.ui.favorite.ui.main.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zulham.mtv.data.local.room.entity.DataEntity
import com.zulham.mtv.data.repository.ShowRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoriteViewModel(private val showRepository: ShowRepository) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()

    fun favMovie(): LiveData<List<DataEntity>>{
        val movie = showRepository.getFavMovie()
        movie.observeForever {
            val error = if (it.isEmpty()) "You Don't Have a Data" else ""
            errorMessage.postValue(error)
        }
        return movie
    }

    fun favTVShow(): LiveData<List<DataEntity>>{
        val tvShow = showRepository.getFavTV()
        tvShow.observeForever {
            val error = if (it.isEmpty()) "You Don't Have a Data" else ""
            errorMessage.postValue(error)
        }
        return tvShow
    }

    fun getError(): LiveData<String> = errorMessage

}