package com.zulham.mtv.data.local.room

import android.content.Context
import androidx.room.*
import com.zulham.mtv.data.local.room.entity.DataEntity
import com.zulham.mtv.data.local.room.entity.DetailEntity
import com.zulham.mtv.data.local.room.entity.listconverter.GenresConverter
import com.zulham.mtv.data.local.room.entity.listconverter.ProductionsConverter
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