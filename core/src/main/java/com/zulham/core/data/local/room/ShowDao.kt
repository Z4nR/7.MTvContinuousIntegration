package com.zulham.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zulham.core.data.local.entity.DataEntity
import com.zulham.core.data.local.entity.DetailEntity
import com.zulham.core.utils.ShowType.MOVIE_TYPE
import com.zulham.core.utils.ShowType.TV_TYPE
import kotlinx.coroutines.flow.Flow

@Dao
interface ShowDao {

    @Query("SELECT * FROM ShowData WHERE isType = $MOVIE_TYPE")
    fun getMovieData(): Flow<List<DataEntity>>

    @Query("SELECT * FROM ShowData WHERE isType = $TV_TYPE")
    fun getTVsData(): Flow<List<DataEntity>>

    @Query("SELECT * FROM ShowDetail WHERE id = :movieId and isType = $MOVIE_TYPE")
    fun getMovieDetail(movieId: Int): Flow<DetailEntity>

    @Query("SELECT * FROM ShowDetail WHERE id = :tvId and isType = $TV_TYPE")
    fun getTVsDetail(tvId: Int): Flow<DetailEntity>

    @Query("SELECT * FROM ShowData WHERE isFav = 1 and isType = $MOVIE_TYPE")
    fun getFavMovie(): Flow<List<DataEntity>>

    @Query("SELECT * FROM ShowData WHERE isFav = 1 and isType = $TV_TYPE")
    fun getFavTV(): Flow<List<DataEntity>>

    @Query("UPDATE ShowData set isFav = 1 WHERE id = :id")
    fun setFav(id: Int)

    @Query("UPDATE ShowData set isFav = 0 WHERE id = :id")
    fun deleteFav(id: Int)

    @Query("SELECT isFav == 1 FROM ShowData WHERE id = :id")
    fun checkFav(id: Int): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShowData(show: List<DataEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShowDetail(detail: DetailEntity)

}