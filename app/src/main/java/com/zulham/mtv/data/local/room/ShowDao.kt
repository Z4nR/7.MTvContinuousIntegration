package com.zulham.mtv.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zulham.mtv.data.local.room.entity.DataEntity
import com.zulham.mtv.data.local.room.entity.DetailEntity
import com.zulham.mtv.utils.ShowType.MOVIE_TYPE
import com.zulham.mtv.utils.ShowType.TV_TYPE

@Dao
interface ShowDao {

    @Query("SELECT * FROM ShowData WHERE isType = $MOVIE_TYPE")
    fun getMovieData(): LiveData<List<DataEntity>>

    @Query("SELECT * FROM ShowData WHERE isType = $TV_TYPE")
    fun getTVsData(): LiveData<List<DataEntity>>

    @Query("SELECT * FROM ShowDetail WHERE id = :movieId and isType = $MOVIE_TYPE")
    fun getMovieDetail(movieId: Int): LiveData<DetailEntity>

    @Query("SELECT * FROM ShowDetail WHERE id = :tvId and isType = $TV_TYPE")
    fun getTVsDetail(tvId: Int): LiveData<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShowData(show: DataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShowDetail(detail: DetailEntity)

}