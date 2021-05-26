package com.zulham.mtv.core.data.local.entity.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.zulham.mtv.core.data.local.entity.GenresItem

@ProvidedTypeConverter
class GenresConverter {

    @TypeConverter
    fun genreListFromString(value: String?): List<GenresItem>? {
        return value?.let { it ->
            val genre = it.split(',')
            return genre.map { GenresItem(it) }
        }
    }

    @TypeConverter
    fun stringFromGenre(genre: List<GenresItem>?): String? {
        val genres = genre?.map { it.name }
        return genres?.joinToString()
    }

}