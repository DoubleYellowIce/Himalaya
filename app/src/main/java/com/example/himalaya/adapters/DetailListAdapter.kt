package com.example.himalaya.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ximalaya.ting.android.opensdk.model.track.Track

class DetailListAdapter:RecyclerView.Adapter<DetailListAdapter.ViewHolder>() {

    var tracks:List<Track>?=null

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        parent.rootView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun setData(tracks: List<Track>) {
        this.tracks=tracks
    }
}