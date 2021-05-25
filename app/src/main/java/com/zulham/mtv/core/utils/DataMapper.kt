package com.zulham.mtv.core.utils

import com.zulham.mtv.core.data.local.entity.DataEntity
import com.zulham.mtv.core.data.remote.response.ResultsMovies
import com.zulham.mtv.core.domain.model.Show
import com.zulham.mtv.core.utils.ShowType.MOVIE_TYPE
import com.zulham.mtv.core.utils.ShowType.TV_TYPE

object DataMapper {

        fun mapPageToDataMovie(input: List<ResultsMovies>): List<DataEntity> {
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
                                MOVIE_TYPE
                        )
                        movieList.add(movies)
                }
                return movieList
        }

        fun mapPageToDataTV(input: List<ResultsMovies>): List<DataEntity> {
                val tvList = ArrayList<DataEntity>()
                input.map {
                        val tvs = DataEntity(
                                it.overview,
                                it.title,
                                it.posterPath,
                                it.backdropPath,
                                it.releaseDate,
                                it.id,
                                false,
                                TV_TYPE
                        )
                        tvList.add(tvs)
                }
                return tvList
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