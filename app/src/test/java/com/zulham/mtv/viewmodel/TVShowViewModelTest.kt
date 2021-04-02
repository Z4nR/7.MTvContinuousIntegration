package com.zulham.mtv.viewmodel

import com.zulham.mtv.ui.movie.MovieViewModel
import com.zulham.mtv.ui.tvshow.TVShowViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TVShowViewModelTest {

    private lateinit var tvViewModel: TVShowViewModel

    @Before
    fun setUp(){
        tvViewModel = TVShowViewModel()
    }

    @Test
    fun getDetail() {
        val tvDetail = tvViewModel.getData()
        Assert.assertNotNull(tvDetail)
    }

}