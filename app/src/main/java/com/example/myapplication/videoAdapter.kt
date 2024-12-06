package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class videoAdapter(
    var Context : Context,
    var videolist : ArrayList<video_data>
) : RecyclerView.Adapter<videoAdapter.VideoViewHolder>(){
    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var videoimage = view.findViewById<ImageView>(R.id.videoimage)
        var videoname = view.findViewById<TextView>(R.id.videoname)
        var videotime = view.findViewById<TextView>(R.id.videotime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_display,parent,false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videolist[position]
        holder.videoname.text = video.video_name
        holder.videotime.text = video.video_time
        holder.videoimage.setImageResource(video.video_img)
    }

    override fun getItemCount(): Int=videolist.size
}