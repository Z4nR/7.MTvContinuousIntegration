package com.zulham.mtv.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zulham.mtv.core.data.ShowRepository
import com.zulham.mtv.core.data.remote.response.ResultsMovies
import com.zulham.mtv.core.utils.DataMapper
import com.zulham.mtv.movie.MovieViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var showRepository: ShowRepository

    @Mock
    private lateinit var  observer: Observer<List<ResultsMovies>>

    @Before
    fun setUp(){
        movieViewModel = MovieViewModel(showRepository)
    }

    @Test
    fun getDetail() {
        val dummyData = DataMapper.generateDummyMovie()
        val resultMovie = arrayListOf<ResultsMovies>()
        dummyData.forEach {
            val entity =  ResultsMovies(
                    it.description,
                    it.title,
                    it.img,
                    it.backdrop,
                    it.releaseDate,
                    it.showId)
            resultMovie.add(entity)
        }
        val movies = MutableLiveData<List<ResultsMovies>>()
        movies.value = resultMovie

        `when`(showRepository.getMovieList(1)).thenReturn(movies)
        movieViewModel.setData(1)
        val movieList = movieViewModel.getData()
        verify(showRepository).getMovieList(1)
        Assert.assertNotNull(movieList)
        Assert.assertEquals( movies , movieList)

        movieViewModel.getData().observeForever(observer)
        verify(observer).onChanged(resultMovie)
    }

}