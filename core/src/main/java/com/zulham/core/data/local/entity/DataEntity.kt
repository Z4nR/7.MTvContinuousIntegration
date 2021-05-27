package com.zulham.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShowData")
data class DataEntity(

    val overview: String? = null,

    val title: String? = null,

    val posterPath: String? = null,

    val backdropPath: String? = null,

    val releaseDate: String? = null,

    @PrimaryKey
    val id: Int? = null,

    val isFav: Boolean? = false,

    val isType: Int? = null

)
