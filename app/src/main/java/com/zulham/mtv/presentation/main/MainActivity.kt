package com.zulham.mtv.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.zulham.mtv.R
import com.zulham.mtv.core.pageradapter.MainSectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var backPressed: Long = 0

    private val rotateOpen by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open) }
    private val rotateClose by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close) }
    private val fromBottom by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom) }
    private val toBottom by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom) }

    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = MainSectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        fabMenu.setOnClickListener {
            onAddButtonCLick()
        }

        fabBookMark.setOnClickListener {
            val uri = Uri.parse("zulham://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        fabLanguage.setOnClickListener {
            val i = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(i)
        }

    }

    private fun onAddButtonCLick() {
        setAnimation(clicked)
        setVisibility(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked){
            fabBookMark.visibility = View.VISIBLE
            fabLanguage.visibility = View.VISIBLE
        } else {
            fabBookMark.visibility = View.INVISIBLE
            fabLanguage.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked){
            fabMenu.startAnimation(rotateOpen)
            fabBookMark.startAnimation(fromBottom)
            fabLanguage.startAnimation(fromBottom)
        } else {
            fabMenu.startAnimation(rotateClose)
            fabBookMark.startAnimation(toBottom)
            fabLanguage.startAnimation(toBottom)
        }
    }

    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            Toast.makeText(baseContext, "Press again to exit", Toast.LENGTH_SHORT).show()
        }
        backPressed = System.currentTimeMillis()
    }
}