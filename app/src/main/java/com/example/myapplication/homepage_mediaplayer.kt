package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.HomepageMediaplayerBinding

class homepage_mediaplayer : AppCompatActivity() {
    lateinit var binding: HomepageMediaplayerBinding
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= HomepageMediaplayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.purple_200)))
        supportActionBar?.title =
            HtmlCompat.fromHtml("<font color='#00000'>MyMediaPlayer</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.closebtnMediaplayer.setOnClickListener(){
            finish()
        }
        binding.toolbarMediaplayer.inflateMenu(R.menu.mediaplayer)
        binding.navviewMediaplayer.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.videobtn -> setCurrentFragment(videofrag_mediaplayer())
                R.id.musicbtn -> setCurrentFragment(musicfrag_mediaplayer())
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragview_mediaplayer, fragment)
            .commit()
    }
}