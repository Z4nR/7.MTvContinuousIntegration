package com.zulham.mtv.core.domain.repository

import androidx.lifecycle.LiveData
import com.zulham.mtv.core.data.Resources
import com.zulham.mtv.core.data.local.entity.DetailEntity
import com.zulham.mtv.core.domain.model.Show

interface IShowRepository {

    fun getMovieList(page_movie: Int): LiveData<Resources<List<Show>>>

    fun getTVShowList(page_tv: Int): LiveData<Resources<List<Show>>>

    fun getMovieDetail(id_movie: Int): LiveData<Resources<DetailEntity>>

    fun getTVDetail(id_tv: Int): LiveData<Resources<DetailEntity>>

    fun getFavMovie(): LiveData<List<Show>>

    fun getFavTV(): LiveData<List<Show>>

    fun checkFav(id: Int): LiveData<Boolean>

    fun setFav(id: Int)

    fun deleteFav(id: Int)

}