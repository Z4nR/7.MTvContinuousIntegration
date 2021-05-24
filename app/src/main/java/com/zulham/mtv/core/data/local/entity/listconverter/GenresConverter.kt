package com.zulham.mtv.core.data.local.entity.listconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.zulham.mtv.core.data.local.entity.GenresItemMovies

@ProvidedTypeConverter
class GenresConverter {

    @TypeConverter
    fun genreListFromString(value: String?): List<GenresItemMovies>? {
        return value?.let { it ->
            val genre = it.split(',')
            return genre.map { GenresItemMovies(it) }
        }
    }

    @TypeConverter
    fun stringFromGenre(genre: List<GenresItemMovies>?): String? {
        val genres = genre?.map { it.name }
        return genres?.joinToString()
    }

}