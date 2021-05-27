package com.zulham.mtv.presentation.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zulham.core.domain.model.Show
import com.zulham.core.ui.ShowAdapter
import com.zulham.core.utils.ShowType.MOVIE_TYPE
import com.zulham.mtv.databinding.FragmentFavoriteBinding
import com.zulham.mtv.presentation.detailfavorite.DetailFavoriteActivity
import com.zulham.mtv.presentation.detailfavorite.DetailFavoriteActivity.Companion.EXTRA_SHOW
import com.zulham.mtv.presentation.detailfavorite.DetailFavoriteActivity.Companion.EXTRA_TYPE
import com.zulham.mtv.presentation.detailfavorite.DetailFavoriteActivity.Companion.MOVIE
import com.zulham.mtv.presentation.detailfavorite.DetailFavoriteActivity.Companion.TV_SHOW
import kotlinx.android.synthetic.main.empty_data.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val filmAdapter = ShowAdapter()

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

        empty_data.visibility = View.GONE

        itemTouchHelper.attachToRecyclerView(binding.rvFavorite)

        filmAdapter.setOnItemClickCallback(object : ShowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Show) {
                val intent = Intent(context, DetailFavoriteActivity::class.java)
                val arg = arguments?.getInt(ARG_SECTION_NUMBER)
                val type = if (arg == MOVIE_TYPE) MOVIE else TV_SHOW
                intent.putExtra(EXTRA_SHOW, data.showId)
                intent.putExtra(EXTRA_TYPE, type)
                startActivity(intent)
            }

        })

        binding.rvFavorite.apply {
            this.adapter = filmAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }

        val favShow = favoriteViewModel.let {
            if (showType == MOVIE_TYPE) it.favMovie() else it.favTVShow()
        }

        favShow.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()){
                showLoading(false)
                filmAdapter.setData(it)
                empty_data.visibility = View.GONE
            } else {
                showLoading(false)
                empty_data.visibility = View.VISIBLE
            }
        })

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.favProgressBar.visibility = View.VISIBLE
        } else {
            binding.favProgressBar.visibility = View.GONE
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null){
                val swipedPosition = viewHolder.adapterPosition
                val dataEntity = filmAdapter.getSwipeData(swipedPosition)
                dataEntity.let { favoriteViewModel.swipeDeleteFav(it) }
            }
        }

    })

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