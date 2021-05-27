package com.zulham.favorite.core.appmodule

import com.zulham.favorite.presentation.detail.DetailFavModuleViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FavDetailModule {

    @InternalCoroutinesApi
    val viewModelModule = module {
        viewModel { DetailFavModuleViewModel(get()) }
    }

}