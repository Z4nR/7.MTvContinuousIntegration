package com.zulham.mtv.data.remote

import androidx.lifecycle.LiveData
import com.zulham.mtv.data.local.room.entity.DataEntity
import com.zulham.mtv.data.local.room.entity.DetailEntity
import com.zulham.mtv.vo.Resources

interface ShowDataSource {

    fun getMovieList(page_movie: Int): LiveData<Resources<List<DataEntity>>>

    fun getTVShowList(page_tv: Int): LiveData<Resources<List<DataEntity>>>

    fun getMovieDetail(id_movie: Int): LiveData<Resources<DetailEntity>>

    fun getTVDetail(id_tv: Int): LiveData<Resources<DetailEntity>>

}