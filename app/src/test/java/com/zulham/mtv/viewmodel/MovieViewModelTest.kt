package com.zulham.mtv.viewmodel

import com.zulham.mtv.ui.movie.MovieViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel()
    }

    @Test
    fun getDetail() {
        val movieDetail = movieViewModel.getData()
        Assert.assertNotNull(movieDetail)
    }

}