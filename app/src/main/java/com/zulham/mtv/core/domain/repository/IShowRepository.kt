package com.zulham.mtv.core.domain.repository

import androidx.lifecycle.LiveData
import com.zulham.mtv.core.data.Resources
import com.zulham.mtv.core.data.local.entity.DetailEntity
import com.zulham.mtv.core.domain.model.Show
import kotlinx.coroutines.flow.Flow

interface IShowRepository {

    fun getMovieList(page_movie: Int): Flow<Resources<List<Show>>>

    fun getTVShowList(page_tv: Int): Flow<Resources<List<Show>>>

    fun getMovieDetail(id_movie: Int): Flow<Resources<DetailEntity>>

    fun getTVDetail(id_tv: Int): Flow<Resources<DetailEntity>>

    fun getFavMovie(): Flow<List<Show>>

    fun getFavTV(): Flow<List<Show>>

    fun checkFav(id: Int): LiveData<Boolean>

    fun setFav(id: Int)

    fun deleteFav(id: Int)

}