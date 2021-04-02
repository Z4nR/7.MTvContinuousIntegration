package com.zulham.mtv.viewmodel

import com.zulham.mtv.data.ShowEntity
import com.zulham.mtv.ui.detail.DetailViewModel
import com.zulham.mtv.utils.DummyData
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    @Mock
    private val dummyData = ShowEntity(showId = "m01",
    title = "Wonder Women 1984",
    genre = "Fantastic, Action, Adventure",
    production = "DC",
    description = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
    backdrop = "https://www.themoviedb.org/t/p/original/fN7f0sajt9uGFX4rPzQdJG3ivCr.jpg",
    releaseDate = "December, 16 2020",
    img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg")

    @Before
    fun setUp(){
        detailViewModel = DetailViewModel()
    }

    @Test
    fun getDetail(){
        val dataDetail = DummyData.generateDummyMovie()[0]
        val getData = dummyData.showId?.let { detailViewModel.setSelectedShow(it) }
        assertNotNull(getData)
        assertEquals(dataDetail, dummyData)
    }

}