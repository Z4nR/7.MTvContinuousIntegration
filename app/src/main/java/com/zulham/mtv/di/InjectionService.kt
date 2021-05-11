package com.zulham.mtv.di

import android.content.Context
import com.zulham.mtv.api.BaseApi
import com.zulham.mtv.api.ServiceApi
import com.zulham.mtv.data.local.LocalDataSource
import com.zulham.mtv.data.local.room.ShowRoomDatabase
import com.zulham.mtv.data.remote.RemoteDataSource
import com.zulham.mtv.data.repository.ShowRepository
import com.zulham.mtv.utils.AppExecutors
import kotlinx.coroutines.InternalCoroutinesApi

object InjectionService {

    @InternalCoroutinesApi
    fun provideRepository(context: Context): ShowRepository{

        val database = ShowRoomDatabase.getInstance(context)

        val retrofit = BaseApi.getInstance().create(ServiceApi::class.java)
        val remoteDataSource = RemoteDataSource.getInstance(retrofit)
        val localDataSource = LocalDataSource.getInstance(database.showDao())
        val appExecutors = AppExecutors()

        return ShowRepository.getInstance(remoteDataSource, localDataSource, appExecutors)

    }

}