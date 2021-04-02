package com.zulham.mtv.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zulham.mtv.data.ShowEntity
import com.zulham.mtv.utils.DummyData

class MovieViewModel: ViewModel() {

    private val listFilm = MutableLiveData<ArrayList<ShowEntity>>()

    fun setData(){
        listFilm.postValue(DummyData.generateDummyMovie())
    }

    fun getData(): LiveData<ArrayList<ShowEntity>> {
        return listFilm
    }

}