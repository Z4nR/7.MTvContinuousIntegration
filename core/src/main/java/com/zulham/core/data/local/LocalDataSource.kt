package com.zulham.core.data.local

import androidx.lifecycle.LiveData
import com.zulham.core.data.local.entity.DataEntity
import com.zulham.core.data.local.entity.DetailEntity
import com.zulham.core.data.local.room.ShowDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource(private val showDao: ShowDao){

    suspend fun insertShowData(show: List<DataEntity>){
        return showDao.insertShowData(show)
    }

    suspend fun insertShowDetail(detail: DetailEntity){
        return showDao.insertShowDetail(detail)
    }

    fun getMovieData(): Flow<List<DataEntity>> {
        return showDao.getMovieData()
    }

    fun getTVsData(): Flow<List<DataEntity>> {
        return showDao.getTVsData()
    }

    fun getDetailMovie(id_movie : Int): Flow<DetailEntity> {
        return showDao.getMovieDetail(id_movie)
    }

    fun getDetailTVShow(id_tv : Int): Flow<DetailEntity> {
        return showDao.getTVsDetail(id_tv)
    }

    suspend fun setFavourite(id: Int){
        return withContext(Dispatchers.IO){ showDao.setFav(id) }
    }

    fun getFavMovie(): Flow<List<DataEntity>> {
        return showDao.getFavMovie()
    }

    fun getFavTV(): Flow<List<DataEntity>> {
        return showDao.getFavTV()
    }

    suspend fun deleteFav(id: Int){
        return withContext(Dispatchers.IO){ showDao.deleteFav(id) }
    }

    fun checkFav(id: Int): LiveData<Boolean>{
        return showDao.checkFav(id)
    }

}