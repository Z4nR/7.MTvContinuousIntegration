package com.zulham.mtv.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShowDetail")
data class DetailEntity(

    val backdropPath: String? = null,

    val overview: String? = null,

    val productionCompanies: List<ProductionCompaniesItem>? = null,

    val releaseDate: String? = null,

    val genres: List<GenresItem>? = null,

    @PrimaryKey
    val id: Int? = null,

    val title: String? = null,

    val posterPath: String? = null,

    val isFav: Boolean? = false,

    val isType: Int? = null

)

data class ProductionCompaniesItem(

    val name: String? = null
)

data class GenresItem(

    val name: String? = null
)
