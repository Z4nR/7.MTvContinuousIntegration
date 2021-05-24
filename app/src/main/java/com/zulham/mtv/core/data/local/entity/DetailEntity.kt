package com.zulham.mtv.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShowDetail")
data class DetailEntity(

    val backdropPath: String? = null,

    val overview: String? = null,

    val productionCompanies: List<ProductionCompaniesItemMovies>? = null,

    val releaseDate: String? = null,

    val genres: List<GenresItemMovies>? = null,

    @PrimaryKey
    val id: Int? = null,

    val title: String? = null,

    val posterPath: String? = null,

    val isFav: Boolean? = false,

    val isType: Int? = null

)

data class ProductionCompaniesItemMovies(

    val name: String? = null
)

data class GenresItemMovies(

    val name: String? = null
)
