package com.zulham.mtv.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zulham.mtv.data.local.room.entity.DataEntity
import com.zulham.mtv.data.remote.response.ResultsMovies
import com.zulham.mtv.data.repository.ShowRepository
import com.zulham.mtv.vo.Resources
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class TVShowViewModel(private val showRepository: ShowRepository): ViewModel() {

    lateinit var listOnline: LiveData<Resources<List<DataEntity>>>

    fun setData(page : Int){
        listOnline = showRepository.getTVShowList(page)
    }

    fun getData(): LiveData<Resources<List<DataEntity>>>{
        return listOnline
    }

}