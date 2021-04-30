package com.zulham.mtv.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.zulham.mtv.data.remote.RemoteDataSource
import com.zulham.mtv.data.remote.response.GenresItemMovies
import com.zulham.mtv.data.remote.response.ProductionCompaniesItemMovies
import com.zulham.mtv.data.remote.response.ResultsMovies
import com.zulham.mtv.data.remote.response.ShowResponseMovies
import com.zulham.mtv.utils.DummyData
import com.zulham.mtv.utils.LiveDataUtilsTest
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val fakeShowRepository = FakeShowRepository(remote)

    private val dummyMovie = DummyData.generateDummyMovie()
    private val dummyTV = DummyData.generateDummyTV()

    private val detailItemTV = ShowResponseMovies(
            "https://www.themoviedb.org/t/p/original/8edzqU74USlnfkCzHtLxILUfQW3.jpg",
            "With his two friends, a video-game-obsessed young man finds himself in a strange version of Tokyo where they must compete in dangerous games to win.",
            productionCompanies= listOf(ProductionCompaniesItemMovies("Netflix")),
            "December, 10 2020", genres=listOf(GenresItemMovies("Drama, Mystery, Action and Adventure")),
            1,
            "Alice in Borderland",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/20mOwAAPwZ1vLQkw0fvuQHiG7bO.jpg")

    private val detailItemFilm = ShowResponseMovies(
            "https://www.themoviedb.org/t/p/original/fN7f0sajt9uGFX4rPzQdJG3ivCr.jpg",
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            productionCompanies= listOf(ProductionCompaniesItemMovies("DC")),
            "December, 16 2020", genres=listOf(GenresItemMovies("Fantastic, Action, Adventure")),
            1,
            "Wonder Women 1984",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg")

    @Test
    fun getMovieList(){
        val dummyMovies = DummyData.generateDummyMovie()
        val resultMovies = arrayListOf<ResultsMovies>()
        dummyMovies.forEach{ dummy ->
            val entity = ResultsMovies(dummy.description, dummy.title, dummy.img, dummy.backdrop, dummy.releaseDate, dummy.showId)
            resultMovies.add(entity)
        }

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadListCallback)
                    .onAllListReceive(resultMovies.toList())
            null
        }.`when`(remote).getMovieList(eq(1), any())

        val movieList = LiveDataUtilsTest.getValue(fakeShowRepository.getMovieList(1))
        verify(remote).getMovieList(eq(1), any())
        Assert.assertNotNull(movieList)
        Assert.assertEquals(dummyMovie.size.toLong(), movieList.size.toLong())
    }

    @Test
    fun getTVList(){
        val dummyTVs = DummyData.generateDummyTV()
        val resultOffline = arrayListOf<ResultsMovies>()
        dummyTVs.forEach{ dummy ->
            val entity = ResultsMovies(dummy.description, dummy.title, dummy.img, dummy.backdrop, dummy.releaseDate, dummy.showId)
            resultOffline.add(entity)
        }

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadListCallback)
                    .onAllListReceive(resultOffline.toList())
            null
        }.`when`(remote).getTVShowList(eq(1), any())

        val tvList = LiveDataUtilsTest.getValue(fakeShowRepository.getTVShowList(1))
        verify(remote).getTVShowList(eq(1), any())
        Assert.assertNotNull(tvList)
        Assert.assertEquals(dummyTV.size.toLong(), tvList.size.toLong())
    }

    @Test
    fun getMovieDetail(){
        val dataDetail = DummyData.generateDummyMovie()[0]
        val production = dataDetail.production?.map { it ->
            ProductionCompaniesItemMovies(it.name)
        }
        val genre = dataDetail.genre?.map { it ->
            GenresItemMovies(it.name)
        }
        val movie = ShowResponseMovies(
                dataDetail.backdrop,
                dataDetail.description,
                production,
                dataDetail.releaseDate,
                genre,
                dataDetail.showId,
                dataDetail.title,
                dataDetail.img)
        val movies = MutableLiveData<ShowResponseMovies>()
        movies.value = movie

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailCallback)
                    .onDetailReceive(detailItemFilm)
            null
        }.`when`(remote).getMovieDetail(eq(1), any())

        val movieDetail = LiveDataUtilsTest.getValue(fakeShowRepository.getMovieDetail(1))
        verify(remote).getMovieDetail(eq(1), any())
        Assert.assertNotNull(movieDetail)
        Assert.assertEquals(movie, movieDetail)
    }

    @Test
    fun getTVDetail(){
        val dataDetail = DummyData.generateDummyTV()[0]
        val production = dataDetail.production?.map { it ->
            ProductionCompaniesItemMovies(it.name)
        }
        val genre = dataDetail.genre?.map { it ->
            GenresItemMovies(it.name)
        }
        val tvs = ShowResponseMovies(
                dataDetail.backdrop,
                dataDetail.description,
                production,
                dataDetail.releaseDate,
                genre,
                dataDetail.showId,
                dataDetail.title,
                dataDetail.img)
        val tv = MutableLiveData<ShowResponseMovies>()
        tv.value = tvs

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailCallback)
                    .onDetailReceive(detailItemTV)
            null
        }.`when`(remote).getTVDetail(eq(1), any())

        val tvDetail = LiveDataUtilsTest.getValue(fakeShowRepository.getTVDetail(1))
        verify(remote).getTVDetail(eq(1), any())
        Assert.assertNotNull(tvDetail)
        Assert.assertEquals(tvs, tvDetail)
    }

}