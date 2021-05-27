package com.zulham.mtv

import android.app.Application
import com.zulham.core.di.AppModule.useCaseModule
import com.zulham.core.di.CoreModule.databaseModule
import com.zulham.core.di.CoreModule.networkModule
import com.zulham.core.di.CoreModule.repositoryModule
import com.zulham.mtv.core.viewmodelmodule.AppModule.viewModelModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyAppModule : Application() {

    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyAppModule)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}