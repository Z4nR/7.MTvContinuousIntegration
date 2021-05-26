package com.zulham.mtv.core.di

import androidx.room.Room
import com.zulham.mtv.core.data.ShowRepository
import com.zulham.mtv.core.data.local.LocalDataSource
import com.zulham.mtv.core.data.local.entity.converter.GenresConverter
import com.zulham.mtv.core.data.local.entity.converter.ProductionsConverter
import com.zulham.mtv.core.data.local.room.ShowRoomDatabase
import com.zulham.mtv.core.data.remote.RemoteDataSource
import com.zulham.mtv.core.data.remote.network.ApiService
import com.zulham.mtv.core.domain.repository.IShowRepository
import com.zulham.mtv.core.utils.AppExecutors
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CoreModule {

    val databaseModule = module {
        factory { get<ShowRoomDatabase>().showDao()}
        single {
            Room.databaseBuilder(
                androidContext(),
                ShowRoomDatabase::class.java,
                "ShowData.db"
            )
                .addTypeConverter(GenresConverter())
                .addTypeConverter(ProductionsConverter())
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    val networkModule = module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

    @InternalCoroutinesApi
    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        factory { AppExecutors() }
        single<IShowRepository> { ShowRepository(get(), get(), get()) }
    }

}