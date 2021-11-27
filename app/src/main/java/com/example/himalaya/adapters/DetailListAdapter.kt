package com.example.himalaya.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.himalaya.R
import com.ximalaya.ting.android.opensdk.model.track.Track
import java.util.*

class DetailListAdapter:RecyclerView.Adapter<DetailListAdapter.ViewHolder>() {

    private var mDetailData=LinkedList<Track>()

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val orderText=view.findViewById<TextView>(R.id.order_text)
        val time=view.findViewById<TextView>(R.id.time)
        val duration=view.findViewById<TextView>(R.id.duration)
        val large_title=view.findViewById<TextView>(R.id.large_title)
        val play_count=view.findViewById<TextView>(R.id.play_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_detail_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track=mDetailData[position]
        holder.apply {
            large_title.text=track.trackTitle
            play_count.text=track.playCount.toString()
            time.text=track.timeline.toString()
            duration.text=track.duration.toString()
            orderText.text=track.orderNum.toString()
        }
    }

    override fun getItemCount(): Int {
        return mDetailData.size
    }

    fun setData(tracks: List<Track>) {
        mDetailData.clear()
        mDetailData.addAll(tracks)
        notifyDataSetChanged()
    }
}