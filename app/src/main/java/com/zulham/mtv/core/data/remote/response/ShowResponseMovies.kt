package com.zulham.mtv.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ShowResponseMovies(

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesItemMovies>? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItemMovies>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
)

data class ProductionCompaniesItemMovies(

	@field:SerializedName("name")
	val name: String? = null
)

data class GenresItemMovies(

	@field:SerializedName("name")
	val name: String? = null
)
