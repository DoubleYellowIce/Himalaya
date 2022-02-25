package com.example.himalaya.interfaces

import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl

interface IPlayerCallback {

    fun onPlayStart()

    fun onPlayPause()

    fun onPlayStop()

    fun onPlayError()

    fun nextPlay(track: Track)

    fun onPrePlay(track: Track)

    fun onListLoaded(list:List<Track>)

    fun onPlayModeChange(playMode: XmPlayListControl.PlayMode)

    fun onProgressChange(currentIndex:Int,total:Int)

    fun onAdLoading()

    fun onAdFinished()


}