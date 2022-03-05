package com.example.himalaya.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.himalaya.R
import com.example.himalaya.base.BaseApplication
import com.example.himalaya.utils.LogUtil
import com.squareup.picasso.Picasso
import com.ximalaya.ting.android.opensdk.model.album.Album
import java.util.*
import kotlin.collections.ArrayList

class RecommendListAdapter:RecyclerView.Adapter<RecommendListAdapter.ViewHolder>() {

    private  var albumList=ArrayList<Album>()

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val albumCover=view.findViewById<ImageView>(R.id.album_cover)
        val albumTitle=view.findViewById<TextView>(R.id.title)
        val albumBrief=view.findViewById<TextView>(R.id.brief)
        val albumViews=view.findViewById<TextView>(R.id.views)
        val albumEpisodes=view.findViewById<TextView>(R.id.number_of_episodes)
    }

    private var mRecommendItemListener: OnRecommendItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recommend_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album=albumList[position]
        holder.apply {
            if (album.coverUrlLarge.isNotEmpty()){
                Picasso.get().load(album.coverUrlLarge).into(albumCover)
            }else{
                Picasso.get().load(R.drawable.ic_pic_loading_fail).into(albumCover)
            }

            albumTitle.text=album.albumTitle
            albumBrief.text=album.albumIntro
            albumViews.text= album.playCount.toString()
            albumEpisodes.text=album.includeTrackCount.toString()+"集"
            itemView.setOnClickListener{
                LogUtil.d(BaseApplication.TestToken,"holder.itemView.setOnClickListener")
                mRecommendItemListener?.onItemClick(albumList.get(position))
            }
        }
    }

    fun setData(albums:List<Album>){
        albumList=albums as ArrayList
        notifyDataSetChanged()
    }

    override fun getItemCount()= albumList.size

    fun setItemClickListener(onRecommendItemClickListener: OnRecommendItemClickListener){
         mRecommendItemListener = onRecommendItemClickListener
    }

    interface OnRecommendItemClickListener{
        fun onItemClick(album: Album)
    }
}