package com.example.myapplication

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentMusicfragMediaplayerBinding

class musicfrag_mediaplayer : Fragment(R.layout.fragment_musicfrag_mediaplayer) {
    private lateinit var mediaPlayer: MediaPlayer
    private val musiclist = ArrayList<music_data>()
    private lateinit var binding: FragmentMusicfragMediaplayerBinding
    private var currentMusic = 0
    private lateinit var animator: ObjectAnimator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicfragMediaplayerBinding.inflate(inflater, container, false)
        mediaPlayer= MediaPlayer()
        // Button listeners
        binding.playMusicplayer.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.playMusicplayer.setImageResource(R.drawable.baseline_play_arrow_24)
                pauseAnimator()
            } else {
                mediaPlayer.start()
                binding.playMusicplayer.setImageResource(R.drawable.baseline_pause_24)
                startAnimator()
            }
        }

        binding.prevMusicplayer.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            currentMusic = if (currentMusic > 0) currentMusic - 1 else musiclist.size - 1
            playMusic(currentMusic)
            binding.currposterMusicplayer.setImageResource(musiclist[currentMusic].music_img)
        }

        binding.nextMusicplayer.setOnClickListener {
            mediaPlayer.stop()
            currentMusic = (currentMusic + 1) % musiclist.size
            playMusic(currentMusic)
            binding.currposterMusicplayer.setImageResource(musiclist[currentMusic].music_img)
        }

        // SeekBar listener
        binding.seekBarMusicplayer.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            var progressChangedValue = 0
            var maxTime=mediaPlayer.duration
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    mediaPlayer.let { player ->
                        if (player.isPlaying) {
                            binding.seekBarMusicplayer.progress = player.currentPosition
                            binding.seekBarMusicplayer.max = player.duration
                        }
                        if (binding.seekBarMusicplayer.progress == binding.seekBarMusicplayer.max) {
                            currentMusic = (currentMusic + 1) % musiclist.size
                            playMusic(currentMusic)
                        }
                    }
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }
                handler.postDelayed(this, 1000)
            }
        }, 0)


        // setting the data for music
        val musicname = arrayOf("Dancing In My Room", "Samjho Na", "Suniya Suniya", "Viva La Vida", "Me And The Devil","Step Back")
        val musicimage = arrayOf(R.drawable.audio1, R.drawable.audio2, R.drawable.audio3, R.drawable.audio4, R.drawable.audio5,R.drawable.audio6)
        val musicurl = arrayOf(R.raw.dancinginmyroom, R.raw.samjhona, R.raw.suniyasuniya, R.raw.vivalavida, R.raw.menandthedevil,R.raw.stepback)

        for (i in musicname.indices) {
            mediaPlayer= MediaPlayer.create(requireContext(), musicurl[i])
            var musictime= mediaPlayer.duration.toInt()
            musiclist.add(music_data(musicname[i], formatDuration(musictime), musicimage[i], musicurl[i]))
        }

        // RecyclerView setup
        binding.musiclistMusicplayer.layoutManager = LinearLayoutManager(requireContext())
        val adapter = musicAdapter(requireContext(), musiclist,mediaPlayer,binding)
// Attach adapter to RecyclerView
        binding.musiclistMusicplayer.layoutManager = LinearLayoutManager(requireContext())
        binding.musiclistMusicplayer.adapter = adapter
        binding.musiclistMusicplayer.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Clicked: ${musiclist[currentMusic].music_name}",
                Toast.LENGTH_SHORT
            ).show()
        }


        return binding.root



    }

    // setting the time for music
    @SuppressLint("DefaultLocale")
    fun formatDuration(milliseconds: Int?): String {
        val seconds = (milliseconds?.div(1000))?.rem(60)
        val minutes = (milliseconds?.div((1000 * 60)))?.rem(60)
        val hours = milliseconds?.div((1000 * 60 * 60))

        return String.format("%02d:%02d", minutes, seconds) // MM:SS format
    }

    private fun playMusic(currentMusic: Int) {
        mediaPlayer.release() // Release the previous instance if any
        val resId = musiclist[currentMusic].music_uri
        binding.playMusicplayer.setImageResource(R.drawable.baseline_pause_24)
        mediaPlayer = MediaPlayer.create(requireContext(), resId).apply {
            setOnCompletionListener {
                binding.playMusicplayer.setImageResource(R.drawable.baseline_play_arrow_24)
            }
            start()
        }
        startAnimator()
    }

    private fun startAnimator(){
        animator = ObjectAnimator.ofFloat(binding.posterMusicplayer, "rotation", 0f, 360f)
        animator.duration = 4000
        animator.repeatCount = ObjectAnimator.INFINITE
//        animator.repeatMode = ObjectAnimator.RESTART
        animator.start()
    }
    private fun pauseAnimator(){
        animator.pause()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
    }
}
