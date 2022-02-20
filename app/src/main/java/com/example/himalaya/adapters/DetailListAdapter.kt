package com.example.himalaya.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.TextView
import com.example.himalaya.R
import com.example.himalaya.base.BaseApplication
import com.example.himalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.Track
import java.text.SimpleDateFormat
import java.util.*

class DetailListAdapter:RecyclerView.Adapter<DetailListAdapter.ViewHolder>() {

    private var mDetailData=LinkedList<Track>()

    private var mItemClickListener: ItemClickListener?=null

    private var mUpdateDateFormat=SimpleDateFormat("yyyy-mm-dd")

    private var mDurationFormat=SimpleDateFormat("mm:ss")

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val orderText=view.findViewById<TextView>(R.id.order_text)
        val updateTime=view.findViewById<TextView>(R.id.update_time)
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
            updateTime.text=mUpdateDateFormat.format(track.updatedAt)
            duration.text=mDurationFormat.format(track.duration*1000)
            orderText.text=track.orderNum.toString()
            itemView.setOnClickListener{
                //when the user click the item
                //the tracks and the position of item will transfer to the DetailActivity
                //so then the playerManger can know which tracks is going to be playing
                //and so on
                mItemClickListener?.onItemClick(mDetailData,position)
            }
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

    fun setItemClickListener(itemClickListener: ItemClickListener){
        mItemClickListener=itemClickListener
    }

    interface ItemClickListener{
        fun onItemClick(mDetailData:List<Track>,position:Int)
    }
}