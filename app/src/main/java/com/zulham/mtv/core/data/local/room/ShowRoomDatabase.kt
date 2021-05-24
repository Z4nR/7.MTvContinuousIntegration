package com.zulham.mtv.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zulham.mtv.core.data.local.entity.DataEntity
import com.zulham.mtv.core.data.local.entity.DetailEntity
import com.zulham.mtv.core.data.local.entity.listconverter.GenresConverter
import com.zulham.mtv.core.data.local.entity.listconverter.ProductionsConverter
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [DataEntity::class, DetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenresConverter::class, ProductionsConverter::class)
abstract class ShowRoomDatabase: RoomDatabase(){

    abstract fun showDao() : ShowDao

    companion object{
        @Volatile
        private var INSTANCE : ShowRoomDatabase? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context): ShowRoomDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    ShowRoomDatabase::class.java,
                    "ShowData.db"
                )
                    .addTypeConverter(GenresConverter())
                    .addTypeConverter(ProductionsConverter())
                    .build().apply {
                    INSTANCE = this
                }
            }
    }

}