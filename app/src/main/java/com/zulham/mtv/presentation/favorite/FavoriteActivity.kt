package com.zulham.mtv.presentation.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.zulham.mtv.core.pageradapter.FavoriteSectionsPagerAdapter
import com.zulham.mtv.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityFavoriteBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}