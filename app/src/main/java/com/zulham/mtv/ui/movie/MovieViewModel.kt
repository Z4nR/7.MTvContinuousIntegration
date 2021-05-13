package com.zulham.mtv.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zulham.mtv.data.local.room.entity.DataEntity
import com.zulham.mtv.data.repository.ShowRepository
import com.zulham.mtv.vo.Resources
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MovieViewModel(private val showRepository: ShowRepository): ViewModel() {

    lateinit var listOnline: LiveData<Resources<List<DataEntity>>>

    fun setDataMovie(page : Int){
        listOnline = showRepository.getMovieList(page)
    }

    fun getDataMovie(): LiveData<Resources<List<DataEntity>>>{
        return listOnline
    }

    fun setDataTV(page: Int){
        listOnline = showRepository.getTVShowList(page)
    }

    fun getDataTV(): LiveData<Resources<List<DataEntity>>>{
        return listOnline
    }

}