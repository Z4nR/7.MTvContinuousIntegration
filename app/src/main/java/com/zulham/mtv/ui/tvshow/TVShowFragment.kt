package com.zulham.mtv.ui.tvshow

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
import com.zulham.mtv.data.remote.response.ResultsMovies
import com.zulham.mtv.ui.detail.DetailActivity
import com.zulham.mtv.ui.detail.DetailActivity.Companion.EXTRA_SHOW
import com.zulham.mtv.ui.detail.DetailActivity.Companion.EXTRA_TYPE
import com.zulham.mtv.ui.detail.DetailActivity.Companion.TV_SHOW
import com.zulham.mtv.utils.Factory
import kotlinx.android.synthetic.main.fragment_t_v_show.*
import kotlinx.coroutines.InternalCoroutinesApi

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@InternalCoroutinesApi
class TVShowFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var tvShowViewModel: TVShowViewModel

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t_v_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)

        val tvShowFactory = Factory.getInstance(requireActivity())
        tvShowViewModel = ViewModelProvider(this, tvShowFactory)[TVShowViewModel::class.java]

        tvShowViewModel.setData(1)

        tvShowViewModel.getData().observe(viewLifecycleOwner, {
            showLoading(false)
            recyclerV(it) }
        )

    }

    private fun recyclerV(films: List<ResultsMovies>) {
        rvTV.apply {
            val filmAdapter = ShowAdapter(films)

            adapter = filmAdapter

            filmAdapter.setOnItemClickCallback(object : ShowAdapter.OnItemClickCallback{
                override fun onItemClicked(data: ResultsMovies) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_SHOW, data.id)
                    intent.putExtra(EXTRA_TYPE, TV_SHOW)
                    startActivity(intent)
                }

            })

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            tvProgressBar.visibility = View.VISIBLE
        } else {
            tvProgressBar.visibility = View.GONE
        }
    }

}