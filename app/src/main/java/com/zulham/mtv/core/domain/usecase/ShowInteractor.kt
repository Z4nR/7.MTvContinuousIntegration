package com.zulham.mtv.core.domain.usecase

import androidx.lifecycle.LiveData
import com.zulham.mtv.core.data.Resources
import com.zulham.mtv.core.data.local.entity.DetailEntity
import com.zulham.mtv.core.domain.model.Show
import com.zulham.mtv.core.domain.repository.IShowRepository

class ShowInteractor(private val showRepository: IShowRepository): ShowUseCase {
    override fun getMovieList(page_movie: Int): LiveData<Resources<List<Show>>> = showRepository.getMovieList(page_movie)

    override fun getTVShowList(page_tv: Int): LiveData<Resources<List<Show>>> = showRepository.getTVShowList(page_tv)

    override fun getMovieDetail(id_movie: Int): LiveData<Resources<DetailEntity>> = showRepository.getMovieDetail(id_movie)

    override fun getTVDetail(id_tv: Int): LiveData<Resources<DetailEntity>> = showRepository.getTVDetail(id_tv)

    override fun getFavMovie(): LiveData<List<Show>> = showRepository.getFavMovie()

    override fun getFavTV(): LiveData<List<Show>> = showRepository.getFavTV()

    override fun checkFav(id: Int): LiveData<Boolean> = showRepository.checkFav(id)

    override fun setFav(id: Int) = showRepository.setFav(id)

    override fun deleteFav(id: Int) = showRepository.deleteFav(id)
}