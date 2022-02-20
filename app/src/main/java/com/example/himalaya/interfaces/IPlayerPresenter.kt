package com.example.himalaya.interfaces

import com.example.himalaya.base.IBasePresenter
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl

interface IPlayerPresenter:IBasePresenter<IPlayerCallback> {

    fun play()

    fun pause()

    fun stop()

    fun playPre()

    fun playNext()

    fun switchPlayMode(mode:XmPlayListControl.PlayMode)

    /**
     * get the list of available tracks
     */
    fun getPlayList()


    fun playByIndex()

    /**
     *when the user move the seekBar,this function will be called
     */
    fun seekTo(progress:Int)
}