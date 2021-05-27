package com.zulham.mtv.presentation.detailfavorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.core.data.local.entity.DetailEntity
import com.zulham.mtv.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class DetailFavoriteActivity : AppCompatActivity() {

    private val detailFavViewModel: DetailFavViewModel by viewModel()

    companion object{

        const val EXTRA_SHOW = "extra_show"
        const val EXTRA_TYPE = "extra_type"
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorite)

        showLoading(true)

        backHome()

        val extras = intent.extras
        if (extras != null) {
            val showId = extras.getInt(EXTRA_SHOW)
            val showType = extras.getString(EXTRA_TYPE)
            detailFavViewModel.getData(showType, showId).observe(this, {
                showDetailFav(it.data)
                showLoading(false)
            })
        }

    }

    private fun showDetailFav(show: DetailEntity?){
        val w = 2000
        val h = 2000
        val imgUrl = "https://image.tmdb.org/t/p/w300/"

        Glide.with(this@DetailFavoriteActivity)
            .load(imgUrl + show?.backdropPath)
            .error(R.drawable.ic_launcher_foreground)
            .apply(RequestOptions().override(w, h))
            .into(IV_posterDetailFav)

        titleDetailFav.text = show?.title
        genreDetailFav.text = show?.genres?.map { genre -> genre.name }?.joinToString()
        showIdFav.text = show?.id.toString()
        showProductionFav.text = show?.productionCompanies?.map { production -> production.name }?.joinToString()
        tv_justified_paragraphFav.text = show?.overview
    }

    private fun backHome() {
        supportActionBar?.title = "Detail Collection"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detailProgressBarFav.visibility = View.VISIBLE
        } else {
            detailProgressBarFav.visibility = View.GONE
        }
    }
}