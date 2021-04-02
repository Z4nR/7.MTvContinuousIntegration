package com.zulham.mtv.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zulham.mtv.R
import com.zulham.mtv.adapter.ShowAdapter
import com.zulham.mtv.data.ShowEntity
import com.zulham.mtv.ui.detail.DetailActivity
import com.zulham.mtv.ui.detail.DetailActivity.Companion.EXTRA_SHOW
import com.zulham.mtv.ui.detail.DetailActivity.Companion.EXTRA_TYPE
import com.zulham.mtv.ui.detail.DetailActivity.Companion.MOVIE
import kotlinx.android.synthetic.main.fragment_movie.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MovieFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)

        movieViewModel.setData()

        movieViewModel.getData().observe(viewLifecycleOwner, {
            recyclerV(it) }
        )

    }

    private fun recyclerV(films: ArrayList<ShowEntity>) {
        rvMovie.apply {
            val filmAdapter = ShowAdapter(films)

            adapter = filmAdapter

            filmAdapter.setOnItemClickCallback(object : ShowAdapter.OnItemClickCallback{
                override fun onItemClicked(data: ShowEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_SHOW, data.showId)
                    intent.putExtra(EXTRA_TYPE, MOVIE)
                    startActivity(intent)
                }

            })

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

}
