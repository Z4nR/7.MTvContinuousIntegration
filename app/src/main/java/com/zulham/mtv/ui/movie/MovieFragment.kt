package com.zulham.mtv.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zulham.mtv.R
import com.zulham.mtv.adapter.ShowAdapter
import com.zulham.mtv.data.local.room.entity.DataEntity
import com.zulham.mtv.ui.detail.DetailActivity
import com.zulham.mtv.ui.detail.DetailActivity.Companion.EXTRA_SHOW
import com.zulham.mtv.ui.detail.DetailActivity.Companion.EXTRA_TYPE
import com.zulham.mtv.ui.detail.DetailActivity.Companion.MOVIE
import com.zulham.mtv.utils.Factory
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.InternalCoroutinesApi

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@InternalCoroutinesApi
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

        showLoading(true)

        val movieFactory = Factory.getInstance(requireActivity())
        movieViewModel = ViewModelProvider(this, movieFactory)[MovieViewModel::class.java]

        movieViewModel.setData(1)

        movieViewModel.getData().observe(viewLifecycleOwner, { it ->
            showLoading(false)
            it.data?.let { recyclerV(it) }
        })

    }

    private fun recyclerV(films: List<DataEntity>) {
        rvMovie.apply {
            val filmAdapter = ShowAdapter(films)

            adapter = filmAdapter

            filmAdapter.setOnItemClickCallback(object : ShowAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_SHOW, data.id)
                    intent.putExtra(EXTRA_TYPE, MOVIE)
                    startActivity(intent)
                }

            })

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            movieProgressBar.visibility = View.VISIBLE
        } else {
            movieProgressBar.visibility = View.GONE
        }
    }

}
