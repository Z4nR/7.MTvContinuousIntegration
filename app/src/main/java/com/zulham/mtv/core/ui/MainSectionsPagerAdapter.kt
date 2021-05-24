package com.zulham.mtv.core.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zulham.mtv.R
import com.zulham.mtv.core.utils.ShowType
import com.zulham.mtv.movie.MovieFragment
import kotlinx.coroutines.InternalCoroutinesApi

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)
class MainSectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    @InternalCoroutinesApi
    override fun getItem(position: Int): Fragment {
        val type = if (position == 0) ShowType.MOVIE_TYPE else ShowType.TV_TYPE
        return MovieFragment.newInstance(type)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int = TAB_TITLES.size

}