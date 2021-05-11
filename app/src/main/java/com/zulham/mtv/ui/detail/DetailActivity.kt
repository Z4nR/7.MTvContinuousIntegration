package com.zulham.mtv.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.mtv.R
import com.zulham.mtv.data.local.room.entity.DetailEntity
import com.zulham.mtv.utils.Factory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
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

        showLoading(true)

        backHome()

        val detailFactory = Factory.getInstance(applicationContext)
        detailViewModel = ViewModelProvider(this, detailFactory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getInt(EXTRA_SHOW)
            val showType = extras.getString(EXTRA_TYPE)
            detailViewModel.getData(showType, showId).observe( this, {
                showDetail(it.data)
                showLoading(false)
            })
        }

    }

    private fun showDetail(show: DetailEntity?){
        val w = 1000
        val h = 1000
        val imgUrl = "https://image.tmdb.org/t/p/w300/"

        Glide.with(this@DetailActivity)
                .load(imgUrl + show?.backdropPath)
                .error(R.drawable.ic_launcher_foreground)
                .apply(RequestOptions().override(w, h))
                .into(IV_posterDetail)

        titleDetail.text = show?.title
        genreDetail.text = show?.genres?.map { genre -> genre.name }?.joinToString()
        showId.text = show?.id.toString()
        showProduction.text = show?.productionCompanies?.map { production -> production.name }?.joinToString()
        tv_justified_paragraph.text = show?.overview
    }

    private fun backHome() {
        supportActionBar?.title = "MTv Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detailProgressBar.visibility = View.VISIBLE
        } else {
            detailProgressBar.visibility = View.GONE
        }
    }

}