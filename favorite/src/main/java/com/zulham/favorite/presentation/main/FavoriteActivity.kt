package com.zulham.favorite.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.zulham.favorite.core.appmodule.FavMainModule.viewModelModule
import com.zulham.favorite.core.pageradapter.FavoriteSectionsPagerAdapter
import com.zulham.favorite.databinding.ActivityFavoriteBinding
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.unloadKoinModules

@InternalCoroutinesApi
class FavoriteActivity : AppCompatActivity() {

private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityFavoriteBinding.inflate(layoutInflater)
     setContentView(binding.root)

        loadKoinModules(viewModelModule)

        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(viewModelModule)
    }
}