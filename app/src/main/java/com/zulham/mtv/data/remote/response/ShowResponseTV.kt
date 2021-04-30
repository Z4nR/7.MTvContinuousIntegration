package com.zulham.mtv.data.remote.response

import com.google.gson.annotations.SerializedName

data class ShowResponseTV(

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesItemTV>? = null,

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItemTV>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val title: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
)

data class ProductionCompaniesItemTV(

	@field:SerializedName("name")
	val name: String? = null
)

data class GenresItemTV(

	@field:SerializedName("name")
	val name: String? = null
)
