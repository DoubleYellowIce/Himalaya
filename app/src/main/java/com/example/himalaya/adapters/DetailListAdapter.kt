package com.example.himalaya.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ximalaya.ting.android.opensdk.model.track.Track
import java.util.*

class DetailListAdapter:RecyclerView.Adapter<DetailListAdapter.ViewHolder>() {

    private var mDetailData=LinkedList<Track>()

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
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