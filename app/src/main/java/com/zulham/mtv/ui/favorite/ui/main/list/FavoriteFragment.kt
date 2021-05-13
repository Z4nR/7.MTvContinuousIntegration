package com.zulham.mtv.ui.favorite.ui.main.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zulham.mtv.adapter.ShowAdapter
import com.zulham.mtv.data.local.room.entity.DataEntity
import com.zulham.mtv.databinding.FragmentFavoriteBinding
import com.zulham.mtv.ui.favorite.ui.main.detail.DetailFavoriteActivity
import com.zulham.mtv.utils.Factory
import com.zulham.mtv.utils.ShowType.MOVIE_TYPE
import kotlinx.android.synthetic.main.empty_data.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val showType = arguments?.getInt(ARG_SECTION_NUMBER)

        showLoading(true)

        val favFactory = Factory.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(this, favFactory)[FavoriteViewModel::class.java]

        val favShow = favoriteViewModel.let {
            if (showType == MOVIE_TYPE) it.favMovie() else it.favTVShow()
        }

        favShow.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()){
                showLoading(false)
                recyclerV(it)
                empty_data.visibility = View.GONE
            } else {
                showLoading(false)
                empty_data.visibility = View.VISIBLE
            }
        })

    }

    private fun recyclerV(films: List<DataEntity>) {
        binding.rvFavorite.apply {
            val filmAdapter = ShowAdapter(films)

            adapter = filmAdapter

            filmAdapter.setOnItemClickCallback(object : ShowAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataEntity) {
                    val intent = Intent(context, DetailFavoriteActivity::class.java)
                    val arg = arguments?.getInt(ARG_SECTION_NUMBER)
                    val type = if (arg == MOVIE_TYPE) DetailFavoriteActivity.MOVIE else DetailFavoriteActivity.TV_SHOW
                    intent.putExtra(DetailFavoriteActivity.EXTRA_SHOW, data.id)
                    intent.putExtra(DetailFavoriteActivity.EXTRA_TYPE, type)
                    startActivity(intent)
                }

            })

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.favProgressBar.visibility = View.VISIBLE
        } else {
            binding.favProgressBar.visibility = View.GONE
        }
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): FavoriteFragment {
            return FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}