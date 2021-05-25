package com.zulham.mtv.core.di

import android.content.Context
import com.zulham.mtv.core.data.ShowRepository
import com.zulham.mtv.core.data.local.LocalDataSource
import com.zulham.mtv.core.data.local.room.ShowRoomDatabase
import com.zulham.mtv.core.data.remote.RemoteDataSource
import com.zulham.mtv.core.data.remote.network.ApiConfig
import com.zulham.mtv.core.domain.repository.IShowRepository
import com.zulham.mtv.core.domain.usecase.ShowInteractor
import com.zulham.mtv.core.domain.usecase.ShowUseCase
import com.zulham.mtv.core.utils.AppExecutors
import kotlinx.coroutines.InternalCoroutinesApi

object InjectionService {

    @InternalCoroutinesApi
    private fun provideRepository(context: Context): IShowRepository {

        val database = ShowRoomDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.showDao())
        val appExecutors = AppExecutors()

        return ShowRepository.getInstance(remoteDataSource, localDataSource, appExecutors)

    }

    @InternalCoroutinesApi
    fun provideShowUseCase(context: Context): ShowUseCase {
        val repository = provideRepository(context)
        return ShowInteractor(repository)
    }

}