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
     * 获取播放列表
     */
    fun getPlayList()


    fun playByIndex()

    /**
     *当用户拖动进度条时，该方法会被回调
     */
    fun seekTo(progress:Int)

    fun isPlaying():Boolean
}