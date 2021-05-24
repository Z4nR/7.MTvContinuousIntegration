package com.zulham.mtv.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulham.mtv.core.di.InjectionService
import com.zulham.mtv.core.domain.usecase.ShowUseCase
import com.zulham.mtv.detail.DetailViewModel
import com.zulham.mtv.favorite.FavoriteViewModel
import com.zulham.mtv.movie.MovieViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class Factory private constructor(private val showUseCase: ShowUseCase): ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance: Factory? = null
        fun getInstance(context: Context): Factory =
                instance ?: synchronized(this){
                    instance ?: Factory(InjectionService.provideShowUseCase(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(showUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(showUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(showUseCase) as T
            }
            else -> throw Throwable(("Unknown ViewModel class: " + modelClass.name))
        }
    }

}