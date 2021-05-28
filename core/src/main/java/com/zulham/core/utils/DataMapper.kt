package com.zulham.core.utils

import com.zulham.core.data.local.entity.DataEntity
import com.zulham.core.data.remote.response.ResultsMovies
import com.zulham.core.data.remote.response.ResultsTV
import com.zulham.core.domain.model.Show
import com.zulham.core.utils.ShowType.MOVIE_TYPE
import com.zulham.core.utils.ShowType.TV_TYPE

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

        fun mapPageToDataTV(input: List<ResultsTV>): List<DataEntity> {
                val tvList = ArrayList<DataEntity>()
                input.map {
                        val tvs = DataEntity(
                                it.overview,
                                it.name,
                                it.posterPath,
                                it.backdropPath,
                                it.firstAirDate,
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

}