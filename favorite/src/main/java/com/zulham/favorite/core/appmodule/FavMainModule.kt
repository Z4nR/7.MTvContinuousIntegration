package com.zulham.favorite.core.appmodule

import com.zulham.favorite.presentation.main.FavoriteModuleViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FavMainModule {

    @InternalCoroutinesApi
    val viewModelModule = module {
        viewModel { FavoriteModuleViewModel(get()) }
    }

}