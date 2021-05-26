package com.zulham.mtv.core.data.local.entity.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.zulham.mtv.core.data.local.entity.ProductionCompaniesItem

@ProvidedTypeConverter
class ProductionsConverter {

    @TypeConverter
    fun productionListFromString(value: String?): List<ProductionCompaniesItem>? {
        return value?.let { it ->
            val production = it.split(',')
            return production.map { ProductionCompaniesItem(it) }
        }
    }

    @TypeConverter
    fun stringFromProduction(production: List<ProductionCompaniesItem>?): String? {
        val productions = production?.map { it.name }
        return productions?.joinToString()
    }

}