package com.example.himalaya.interfaces

import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.Track

interface IAlbumDetailViewCallback {

    /**
     *
     */
    fun onDetailListLoaded(tracks:List<Track>)

    fun onAlbumLoaded(album: Album)

    fun onNetworkError()
}