package com.zulham.mtv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zulham.mtv.data.ShowEntity
import com.zulham.mtv.ui.detail.DetailActivity.Companion.MOVIE
import com.zulham.mtv.ui.detail.DetailActivity.Companion.TV_SHOW
import com.zulham.mtv.utils.DummyData

class DetailViewModel: ViewModel() {
    private lateinit var showId: String

    fun setSelectedShow(showId: String) {
        this.showId = showId
    }

    fun getFilm(type: String?): ShowEntity {
        lateinit var show: ShowEntity
        when (type) {
            MOVIE -> {
                for (showEntity in DummyData.generateDummyMovie()) {
                    if (showEntity.showId == showId) {
                        show = showEntity
                    }
                }
            }
            TV_SHOW -> {
                for (showEntity in DummyData.generateDummyTV()) {
                    if (showEntity.showId == showId) {
                        show = showEntity
                    }
                }
            }
        }
        return show
    }
}