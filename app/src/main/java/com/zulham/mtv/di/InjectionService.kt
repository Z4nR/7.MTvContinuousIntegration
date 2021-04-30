package com.zulham.mtv.di

import android.content.Context
import com.zulham.mtv.api.BaseApi
import com.zulham.mtv.api.ServiceApi
import com.zulham.mtv.data.remote.RemoteDataSource
import com.zulham.mtv.data.repository.ShowRepository
import kotlinx.coroutines.InternalCoroutinesApi

object InjectionService {

    @InternalCoroutinesApi
    fun provideRepository(context: Context): ShowRepository{

        val retrofit = BaseApi.getInstance().create(ServiceApi::class.java)
        val remoteDataSource = RemoteDataSource.getInstance(retrofit)

        return ShowRepository.getInstance(remoteDataSource)

    }

}