package com.zulham.mtv.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulham.mtv.data.repository.ShowRepository
import com.zulham.mtv.di.InjectionService
import com.zulham.mtv.ui.detail.DetailViewModel
import com.zulham.mtv.ui.movie.MovieViewModel
import com.zulham.mtv.ui.tvshow.TVShowViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class Factory private constructor(private val mShowRepository: ShowRepository): ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance: Factory? = null
        fun getInstance(context: Context): Factory =
                instance ?: synchronized(this){
                    instance ?: Factory(InjectionService.provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mShowRepository) as T
            }
            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                TVShowViewModel(mShowRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mShowRepository) as T
            }
            else -> throw Throwable(("Unknown ViewModel class: " + modelClass.name))
        }
    }

}