package com.zulham.mtv.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zulham.core.data.Resources
import com.zulham.core.domain.model.Show
import com.zulham.core.ui.ShowAdapter
import com.zulham.core.utils.ShowType.MOVIE_TYPE
import com.zulham.mtv.R
import com.zulham.mtv.presentation.detail.DetailActivity
import com.zulham.mtv.presentation.detail.DetailActivity.Companion.EXTRA_SHOW
import com.zulham.mtv.presentation.detail.DetailActivity.Companion.EXTRA_TYPE
import com.zulham.mtv.presentation.detail.DetailActivity.Companion.MOVIE
import com.zulham.mtv.presentation.detail.DetailActivity.Companion.TV_SHOW
import kotlinx.android.synthetic.main.error_data.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@InternalCoroutinesApi
class MovieFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private val movieViewModel: MovieViewModel by viewModel()

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

        val showType = arguments?.getInt(ARG_SECTION_NUMBER)

        showLoading(true)

        val filmAdapter = ShowAdapter()
        filmAdapter.setOnItemClickCallback(object : ShowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Show) {
                val intent = Intent(context, DetailActivity::class.java)
                val arg = arguments?.getInt(ARG_SECTION_NUMBER)
                val type = if (arg == MOVIE_TYPE) MOVIE else TV_SHOW
                intent.putExtra(EXTRA_SHOW, data.showId)
                intent.putExtra(EXTRA_TYPE, type)
                startActivity(intent)
            }

        })

        rvMovie.apply {
            this.adapter = filmAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }

        movieViewModel.let {
            if (showType == MOVIE_TYPE) it.setDataMovie(1) else it.setDataTV(1)
        }

        val getData = movieViewModel.let {
            if (showType == MOVIE_TYPE) it.getDataMovie() else it.getDataTV()
        }

        getData.observe(viewLifecycleOwner, {
            if (it != null){
                when (it) {
                    is Resources.Loading -> {
                        showLoading(true)
                        error_data.visibility = View.GONE
                    }
                    is Resources.Success -> {
                        showLoading(false)
                        filmAdapter.setData(it.data)
                        error_data.visibility = View.GONE
                    }
                    is Resources.Error -> {
                        showLoading(false)
                        Toast.makeText(context, "Something was error, we will check it", Toast.LENGTH_SHORT).show()
                        error_data.visibility = View.VISIBLE
                    }
                }
            }
        })

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            favProgressBar.visibility = View.VISIBLE
        } else {
            favProgressBar.visibility = View.GONE
        }
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): MovieFragment {
            return MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

}
