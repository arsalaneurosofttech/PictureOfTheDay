package com.arsalan.pictureoftheday

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.arsalan.pictureoftheday.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    private val SPLASH_DISPLAY_LENGTH = 3000
    private val logo: ImageView? = null
    private var slideAnimation: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        logo!!.startAnimation(slideAnimation)


        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
        }, SPLASH_DISPLAY_LENGTH.toLong())

    }
}