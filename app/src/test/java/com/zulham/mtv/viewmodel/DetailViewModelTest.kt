package com.zulham.mtv.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zulham.mtv.core.data.ShowRepository
import com.zulham.mtv.core.data.remote.response.GenresItemMovies
import com.zulham.mtv.core.data.remote.response.ProductionCompaniesItemMovies
import com.zulham.mtv.core.data.remote.response.ShowResponseMovies
import com.zulham.mtv.core.domain.model.Genres
import com.zulham.mtv.core.domain.model.PH
import com.zulham.mtv.core.domain.model.ShowEntity
import com.zulham.mtv.core.utils.DataMapper
import com.zulham.mtv.detail.DetailActivity.Companion.MOVIE
import com.zulham.mtv.detail.DetailActivity.Companion.TV_SHOW
import com.zulham.mtv.detail.DetailViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    private val dummyMovie = ShowEntity(1,
            "Wonder Women 1984",
            "December, 16 2020",
            genre = listOf(Genres("Fantastic, Action, Adventure")),
            production = listOf(PH("DC")),
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "https://www.themoviedb.org/t/p/original/fN7f0sajt9uGFX4rPzQdJG3ivCr.jpg")

    private val dummyTV = ShowEntity(1,
            "Alice in Borderland",
            "December, 10 2020",
            genre = listOf(Genres("Drama, Mystery, Action and Adventure")),
            production = listOf(PH("Netflix")),
            "With his two friends, a video-game-obsessed young man finds himself in a strange version of Tokyo where they must compete in dangerous games to win.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/20mOwAAPwZ1vLQkw0fvuQHiG7bO.jpg",
            "https://www.themoviedb.org/t/p/original/8edzqU74USlnfkCzHtLxILUfQW3.jpg")


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var showRepository: ShowRepository

    @Mock
    private lateinit var  observer: Observer<ShowResponseMovies>

    @Before
    fun setUp(){
        detailViewModel = DetailViewModel(showRepository)
    }

    @Test
    fun getDetailMovie(){
        val dataDetail = DataMapper.generateDummyMovie()[0]
        val production = dataDetail.production.map { it ->
            ProductionCompaniesItemMovies(it.name)
        }
        val genre = dataDetail.genre.map { it ->
            GenresItemMovies(it.name)
        }
        val moviesDetail = ShowResponseMovies(
                dataDetail.backdrop,
                dataDetail.description,
                production,
                dataDetail.releaseDate,
                genre,
                dataDetail.showId,
                dataDetail.title,
                dataDetail.img)
        val movies = MutableLiveData<ShowResponseMovies>()
        movies.value = moviesDetail

        `when`(showRepository.getMovieDetail(1)).thenReturn(movies)
        val getData = dummyMovie.showId.let { detailViewModel.setSelectedShow(it) }
        dummyMovie.showId.let { detailViewModel.getData(MOVIE, it) }
        verify(showRepository).getMovieDetail(1)
        assertNotNull(getData)
        assertEquals(dataDetail, dummyMovie)

        detailViewModel.getData(MOVIE, 1).observeForever(observer)
        Mockito.verify(observer).onChanged(moviesDetail)
    }

    @Test
    fun getDetailTV(){
        val dataDetail = DataMapper.generateDummyTV()[0]
        val production = dataDetail.production.map { it ->
            ProductionCompaniesItemMovies(it.name)
        }
        val genre = dataDetail.genre.map { it ->
            GenresItemMovies(it.name)
        }
        val tvDetail = ShowResponseMovies(
                dataDetail.backdrop,
                dataDetail.description,
                production,
                dataDetail.releaseDate,
                genre,
                dataDetail.showId,
                dataDetail.title,
                dataDetail.img)
        val tv = MutableLiveData<ShowResponseMovies>()
        tv.value = tvDetail

        `when`(showRepository.getTVDetail(1)).thenReturn(tv)
        val getData = dummyTV.showId.let { detailViewModel.setSelectedShow(it) }
        dummyTV.showId.let { detailViewModel.getData(TV_SHOW, it) }
        verify(showRepository).getTVDetail(1)
        assertNotNull(getData)
        assertEquals(dataDetail, dummyTV)

        detailViewModel.getData(TV_SHOW, 1).observeForever(observer)
        Mockito.verify(observer).onChanged(tvDetail)
    }

}