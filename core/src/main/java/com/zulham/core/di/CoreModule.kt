package com.zulham.core.di

import androidx.room.Room
import com.zulham.core.data.local.LocalDataSource
import com.zulham.core.data.local.entity.converter.GenresConverter
import com.zulham.core.data.local.entity.converter.ProductionsConverter
import com.zulham.core.data.local.room.ShowRoomDatabase
import com.zulham.core.data.remote.RemoteDataSource
import com.zulham.core.data.remote.network.ApiService
import com.zulham.core.domain.repository.IShowRepository
import kotlinx.coroutines.InternalCoroutinesApi
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CoreModule {

    val databaseModule = module {
        single { get<ShowRoomDatabase>().showDao()}
        single {
            val passphrase: ByteArray = SQLiteDatabase.getBytes("zan_zulham".toCharArray())
            val factory = SupportFactory(passphrase)
            Room.databaseBuilder(
                androidContext(),
                ShowRoomDatabase::class.java,
                "ShowData.db"
            )
                .addTypeConverter(GenresConverter())
                .addTypeConverter(ProductionsConverter())
                .fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .build()
        }
    }

    val networkModule = module {
        single {
            val hostname = "api.themoviedb.org"
            val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
                .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
                .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
                .build()
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
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
        single<IShowRepository> { com.zulham.core.data.ShowRepository(get(), get()) }
    }

}