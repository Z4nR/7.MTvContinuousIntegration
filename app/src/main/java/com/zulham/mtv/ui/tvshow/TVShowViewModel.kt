package com.zulham.mtv.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zulham.mtv.data.ShowEntity
import com.zulham.mtv.utils.DummyData

class TVShowViewModel: ViewModel() {

    private val listTV = MutableLiveData<ArrayList<ShowEntity>>()

    fun setData(){
        listTV.postValue(DummyData.generateDummyTV())
    }

    fun getData(): LiveData<ArrayList<ShowEntity>> {
        return listTV
    }

}