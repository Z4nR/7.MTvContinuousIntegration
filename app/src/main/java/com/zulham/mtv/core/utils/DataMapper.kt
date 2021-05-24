package com.zulham.mtv.core.utils

import com.zulham.mtv.core.data.local.entity.DataEntity
import com.zulham.mtv.core.data.remote.response.ResultsMovies
import com.zulham.mtv.core.domain.model.Show

object DataMapper {

        fun mapPageToData(input: List<ResultsMovies>): List<DataEntity> {
                val movieList = ArrayList<DataEntity>()
                input.map {
                        val movies = DataEntity(
                                it.overview,
                                it.title,
                                it.posterPath,
                                it.backdropPath,
                                it.releaseDate,
                                it.id,
                                false,
                                null
                        )
                        movieList.add(movies)
                }
                return movieList
        }

        fun mapDataToShow(input: List<DataEntity>): List<Show> =
                input.map {
                        Show(
                                it.id,
                                it.title,
                                it.releaseDate,
                                null,
                                null,
                                it.overview,
                                it.posterPath,
                                it.backdropPath
                        )
                }

        fun mapShowToData(input: Show) = DataEntity(
                input.description,
                input.title,
                input.img,
                input.backdrop,
                input.releaseDate,
                input.showId,
                false,
                null
        )

}