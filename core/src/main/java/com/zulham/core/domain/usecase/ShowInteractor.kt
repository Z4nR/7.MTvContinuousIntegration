package com.zulham.core.domain.usecase

import androidx.lifecycle.LiveData
import com.zulham.core.data.Resources
import com.zulham.core.data.local.entity.DetailEntity
import com.zulham.core.domain.model.Show
import com.zulham.core.domain.repository.IShowRepository
import kotlinx.coroutines.flow.Flow

class ShowInteractor(private val showRepository: IShowRepository): ShowUseCase {
    override fun getMovieList(page_movie: Int): Flow<Resources<List<Show>>> = showRepository.getMovieList(page_movie)

    override fun getTVShowList(page_tv: Int): Flow<Resources<List<Show>>> = showRepository.getTVShowList(page_tv)

    override fun getMovieDetail(id_movie: Int): Flow<Resources<DetailEntity>> = showRepository.getMovieDetail(id_movie)

    override fun getTVDetail(id_tv: Int): Flow<Resources<DetailEntity>> = showRepository.getTVDetail(id_tv)

    override fun getFavMovie(): Flow<List<Show>> = showRepository.getFavMovie()

    override fun getFavTV(): Flow<List<Show>> = showRepository.getFavTV()

    override fun checkFav(id: Int): LiveData<Boolean> = showRepository.checkFav(id)

    override suspend fun setFav(id: Int) = showRepository.setFav(id)

    override suspend fun deleteFav(id: Int) = showRepository.deleteFav(id)
}