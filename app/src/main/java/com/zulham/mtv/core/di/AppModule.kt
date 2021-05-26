package com.zulham.mtv.core.di

import com.zulham.mtv.core.domain.usecase.ShowInteractor
import com.zulham.mtv.core.domain.usecase.ShowUseCase
import com.zulham.mtv.detail.DetailViewModel
import com.zulham.mtv.detailfavorite.DetailFavViewModel
import com.zulham.mtv.favorite.FavoriteViewModel
import com.zulham.mtv.movie.MovieViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val useCaseModule = module {
        factory<ShowUseCase> { ShowInteractor(get()) }
    }

    @InternalCoroutinesApi
    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { FavoriteViewModel(get()) }
        viewModel { DetailViewModel(get()) }
        viewModel { DetailFavViewModel(get()) }
    }

}