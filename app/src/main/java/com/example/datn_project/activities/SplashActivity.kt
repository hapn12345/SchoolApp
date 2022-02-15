package com.example.datn_project.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.datn_project.R
import com.example.datn_project.Utils.makeStatusBarTransparent
import com.example.datn_project.databinding.ActivitySplashBinding
import com.example.datn_project.utilities.SharedPreferenceUtil


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeStatusBarTransparent(this)
        binding.imgSplash.setAnimation(R.raw.splash_animation)
        binding.imgSplash.speed = 1.5f
        binding.imgSplash.playAnimation()
        binding.imgSplash.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                val i = Intent(this@SplashActivity, LoginActivity::class.java)
                if (SharedPreferenceUtil.readAccessToken(this@SplashActivity) == "") {
                    startActivity(i)
                } else {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }
                finish()
            }
        })
    }
}