package com.zulham.mtv.core.viewmodelmodule

import com.zulham.mtv.presentation.detail.DetailViewModel
import com.zulham.mtv.presentation.detailfavorite.DetailFavViewModel
import com.zulham.mtv.presentation.favorite.FavoriteViewModel
import com.zulham.mtv.presentation.movie.MovieViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    @InternalCoroutinesApi
    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { FavoriteViewModel(get()) }
        viewModel { DetailViewModel(get()) }
        viewModel { DetailFavViewModel(get()) }
    }

}