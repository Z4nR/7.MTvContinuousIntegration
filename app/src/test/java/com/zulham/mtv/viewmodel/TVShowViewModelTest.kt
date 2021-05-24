package com.zulham.mtv.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zulham.mtv.core.data.ShowRepository
import com.zulham.mtv.core.data.remote.response.ResultsMovies
import com.zulham.mtv.core.ui.tvshow.TVShowViewModel
import com.zulham.mtv.core.utils.DataMapper
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
class TVShowViewModelTest {

    private lateinit var tvViewModel: TVShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var showRepository: ShowRepository

    @Mock
    private lateinit var  observer: Observer<List<ResultsMovies>>

    @Before
    fun setUp(){
        tvViewModel = TVShowViewModel(showRepository)
    }

    @Test
    fun getDetail() {
        val dummyData = DataMapper.generateDummyTV()
        val resultTV = arrayListOf<ResultsMovies>()
        dummyData.forEach {
            val entity =  ResultsMovies(
                it.description,
                it.title,
                it.img,
                it.backdrop,
                it.releaseDate,
                it.showId)
            resultTV.add(entity)
        }
        val tv = MutableLiveData<List<ResultsMovies>>()
        tv.value = resultTV

        `when`(showRepository.getTVShowList(1)).thenReturn(tv)
        tvViewModel.setData(1)
        val tvList = tvViewModel.getData()
        verify(showRepository)?.getTVShowList(1)
        Assert.assertNotNull(tvList)
        Assert.assertEquals( tv , tvList)

        tvViewModel.getData().observeForever(observer)
        verify(observer).onChanged(resultTV)
    }

}