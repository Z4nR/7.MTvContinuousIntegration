package com.zulham.favorite.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.core.data.local.entity.DetailEntity
import com.zulham.favorite.core.appmodule.FavDetailModule.viewModelModule
import com.zulham.favorite.databinding.ActivityDetailFavoriteBinding
import com.zulham.mtv.R
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@InternalCoroutinesApi
class DetailFavoriteModuleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFavoriteBinding

    private val detailFavViewModel: DetailFavModuleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(viewModelModule)

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

        Glide.with(this@DetailFavoriteModuleActivity)
            .load(imgUrl + show?.backdropPath)
            .error(R.drawable.ic_launcher_foreground)
            .apply(RequestOptions().override(w, h))
            .into(binding.IVPosterDetailFav)

        binding.titleDetailFav.text = show?.title
        binding.genreDetailFav.text = show?.genres?.map { genre -> genre.name }?.joinToString()
        binding.showIdFav.text = show?.id.toString()
        binding.showProductionFav.text = show?.productionCompanies?.map { production -> production.name }?.joinToString()
        binding.tvJustifiedParagraphFav.text = show?.overview
    }

    private fun backHome() {
        supportActionBar?.title = getString(R.string.detailcollection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.detailProgressBarFav.visibility = View.VISIBLE
        } else {
            binding.detailProgressBarFav.visibility = View.GONE
        }
    }

    companion object{

        const val EXTRA_SHOW = "extra_show"
        const val EXTRA_TYPE = "extra_type"
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"

    }

}