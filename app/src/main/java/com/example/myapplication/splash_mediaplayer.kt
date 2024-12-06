package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.ComponentActivity

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.SplashMediaplayerBinding
import kotlinx.coroutines.*

class splashMediaplayer : ComponentActivity() {
    private lateinit var binding: SplashMediaplayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= SplashMediaplayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Example: Add logic to navigate to the main activity after a delay
        navigateToNextScreen()
    }

    private fun navigateToNextScreen() {
        // animation to rotate the circle
        val animator = ObjectAnimator.ofFloat(binding.imageSplash, "rotation", 0f, 360f)
        animator.duration = 900
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.RESTART
        animator.start()

        binding.imageSplash.postDelayed({
            val intent = Intent(this, homepage_mediaplayer::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}
