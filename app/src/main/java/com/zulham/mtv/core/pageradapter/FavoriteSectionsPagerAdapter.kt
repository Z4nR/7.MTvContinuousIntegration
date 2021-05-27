package com.zulham.mtv.core.pageradapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zulham.core.R
import com.zulham.core.utils.ShowType.MOVIE_TYPE
import com.zulham.core.utils.ShowType.TV_TYPE
import com.zulham.mtv.presentation.favorite.FavoriteFragment
import kotlinx.coroutines.InternalCoroutinesApi

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)

class FavoriteSectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    @InternalCoroutinesApi
    override fun getItem(position: Int): Fragment {
        val type = if (position == 0) MOVIE_TYPE else TV_TYPE
        return FavoriteFragment.newInstance(type)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int = TAB_TITLES.size
}