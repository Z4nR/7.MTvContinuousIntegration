package com.zulham.mtv.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowEntity(
    val showId: String? = "",
    val title: String? = "",
    val releaseDate: String? = "",
    val genre: String? = "",
    val production: String? = "",
    val description: String? = "",
    val img: String? = "",
    val backdrop: String? = ""
) : Parcelable
