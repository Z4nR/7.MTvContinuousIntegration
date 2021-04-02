package com.zulham.mtv.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.mtv.R
import com.zulham.mtv.data.ShowEntity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.list_item.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    companion object{

        const val EXTRA_SHOW = "extra_show"
        const val EXTRA_TYPE = "extra_type"
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        backHome()

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getString(EXTRA_SHOW)
            val showType = extras.getString(EXTRA_TYPE)
            if (showId != null) {
                detailViewModel.setSelectedShow(showId)
                showDetail(detailViewModel.getFilm(showType))
            }
        }

    }

    private fun showDetail(show: ShowEntity){
        val w = 1000
        val h = 1000

        Glide.with(this@DetailActivity)
                .load(show.backdrop)
                .apply(RequestOptions().override(w, h))
                .into(IV_posterDetail)

        titleDetail.text = show.title
        genreDetail.text = show.genre
        showId.text = show.showId
        showProduction.text = show.production
        tv_justified_paragraph.text = show.description
    }

    private fun backHome() {
        supportActionBar?.title = "MTv Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}