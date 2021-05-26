package com.zulham.mtv.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zulham.mtv.core.data.local.entity.DataEntity
import com.zulham.mtv.core.data.local.entity.DetailEntity
import com.zulham.mtv.core.data.local.entity.converter.GenresConverter
import com.zulham.mtv.core.data.local.entity.converter.ProductionsConverter

@Database(entities = [DataEntity::class, DetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenresConverter::class, ProductionsConverter::class)
abstract class ShowRoomDatabase: RoomDatabase(){

    abstract fun showDao() : ShowDao

}