package com.zulham.core.di

import com.zulham.core.domain.usecase.ShowInteractor
import com.zulham.core.domain.usecase.ShowUseCase
import org.koin.dsl.module

object AppModule {

    val useCaseModule = module {
        factory<ShowUseCase> { ShowInteractor(get()) }
    }

}