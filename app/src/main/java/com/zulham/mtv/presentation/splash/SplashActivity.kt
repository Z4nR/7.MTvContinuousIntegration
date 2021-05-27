package com.zulham.mtv.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.zulham.mtv.R
import com.zulham.mtv.presentation.main.MainActivity
import kotlinx.coroutines.InternalCoroutinesApi

class SplashActivity : AppCompatActivity() {
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashScreenTimeOut = 3000
        val homeIntent = Intent(this, MainActivity::class.java)

        Handler(mainLooper).postDelayed({
            startActivity(homeIntent)
            finish()
        }, splashScreenTimeOut.toLong())

    }
}