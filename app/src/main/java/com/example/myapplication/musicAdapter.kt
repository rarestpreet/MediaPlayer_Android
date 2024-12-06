package com.example.myapplication

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentMusicfragMediaplayerBinding

class musicAdapter(
    private val context: Context,
    private val musicList: ArrayList<music_data>,
    private val mediaPlayer: MediaPlayer?,
    private var binding: FragmentMusicfragMediaplayerBinding
) : RecyclerView.Adapter<musicAdapter.MusicViewHolder>() {

    // Define ViewHolder class to hold references to the views
    class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val musicName: TextView = view.findViewById(R.id.musicname_md)
        val musicTime: TextView = view.findViewById(R.id.musictime_md)
        val musicImage: ImageView = view.findViewById(R.id.musicimage_md)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.music_display, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val music = musicList[position]
        holder.musicName.text = music.music_name
        holder.musicTime.text = music.music_time
        holder.musicImage.setImageResource(music.music_img) // Set image

        // Set click listener on the item view
        holder.itemView.setOnClickListener {
            onItemClicked(music) // Pass clicked item data to the lambda function
        }
    }

    private fun onItemClicked(music: music_data) {
        Toast.makeText(context, "Playing ${music.music_name}", Toast.LENGTH_SHORT).show()

        // Reset the mediaPlayer if it is already playing
        mediaPlayer?.reset()

        // Get URI for the new music
        val uri = Uri.parse("android.resource://${context.packageName}/${music.music_uri}")

        try {
            // Set the new data source and prepare mediaPlayer
            mediaPlayer?.setDataSource(context, uri)
            mediaPlayer?.prepare()  // Prepare the media player
            binding.playMusicplayer.setImageResource(R.drawable.baseline_pause_24)
            binding.currnameMusicplayer.text=music.music_name
            binding.currposterMusicplayer.setImageResource(music.music_img)
            mediaPlayer?.start()

        } catch (e: Exception) {
            Toast.makeText(context, "Error playing music", Toast.LENGTH_SHORT).show()
        }

    }


    override fun getItemCount(): Int {
        return musicList.size
    }
}
