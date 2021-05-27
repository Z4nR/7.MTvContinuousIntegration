package com.zulham.mtv.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zulham.core.data.Resources
import com.zulham.core.domain.model.Show
import com.zulham.core.domain.usecase.ShowUseCase
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MovieViewModel(private val showUseCase: ShowUseCase): ViewModel() {

    private lateinit var listOnline: LiveData<Resources<List<Show>>>

    fun setDataMovie(page : Int){
        listOnline = showUseCase.getMovieList(page).asLiveData()
    }

    fun getDataMovie(): LiveData<Resources<List<Show>>>{
        return listOnline
    }

    fun setDataTV(page: Int){
        listOnline = showUseCase.getTVShowList(page).asLiveData()
    }

    fun getDataTV(): LiveData<Resources<List<Show>>>{
        return listOnline
    }

}