package com.zulham.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Show(
        val showId: Int? = null,
        val title: String? = "",
        val releaseDate: String? = "",
        val genre: List<Genres>? = null,
        val production: List<PH>? = null,
        val description: String? = "",
        val img: String? = "",
        val backdrop: String? = ""
) : Parcelable

@Parcelize
data class PH (
        val name: String? = ""
) : Parcelable

@Parcelize
data class Genres (
        val name: String? = ""
) : Parcelable
