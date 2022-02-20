package com.example.himalaya.presenters

import com.example.himalaya.base.BaseApplication
import com.example.himalaya.interfaces.IPlayerCallback
import com.example.himalaya.interfaces.IPlayerPresenter
import com.example.himalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.model.PlayableModel
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList
import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager
import com.ximalaya.ting.android.opensdk.player.advertis.IXmAdsStatusListener
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException

class PlayerPresenter private constructor():IPlayerPresenter, IXmAdsStatusListener,
    IXmPlayerStatusListener {

    private var isPlayListSet=false

    companion object{

        val playerPresenter=PlayerPresenter()

        private val mPlayerManager: XmPlayerManager =XmPlayerManager.getInstance(BaseApplication.sContext).apply {
            addAdsStatusListener(playerPresenter)
            addPlayerStatusListener(playerPresenter)
        }
    }

    fun setPlayList(tracks:List<Track>,index:Int){
        isPlayListSet=true
        mPlayerManager.setPlayList(tracks,index)
    }

    override fun play() {
        if (isPlayListSet){
            mPlayerManager.play()
        }
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun playPre() {
        TODO("Not yet implemented")
    }

    override fun playNext() {
        TODO("Not yet implemented")
    }

    override fun switchPlayMode(mode: XmPlayListControl.PlayMode) {
        TODO("Not yet implemented")
    }

    override fun getPlayList() {
        TODO("Not yet implemented")
    }

    override fun playByIndex() {
        TODO("Not yet implemented")
    }

    override fun seekTo(progress: Int) {
        TODO("Not yet implemented")
    }

    override fun registerViewCallback(t: IPlayerCallback) {
        TODO("Not yet implemented")
    }

    override fun unregisterViewCallback(t: IPlayerCallback) {
        TODO("Not yet implemented")
    }

    //all the functions below are the callback of the AD player
    override fun onStartGetAdsInfo() {
        LogUtil.d(BaseApplication.TestToken,"PlayerPresenter onStartGetAdsInfo")

    }

    override fun onGetAdsInfo(p0: AdvertisList?) {
        LogUtil.d(BaseApplication.TestToken,"PlayerPresenter onStartGetAdsInfo")
    }

    override fun onAdsStartBuffering() {
        LogUtil.d(BaseApplication.TestToken,"PlayerPresenter onAdsStartBuffering")
    }

    override fun onAdsStopBuffering() {
        LogUtil.d(BaseApplication.TestToken,"PlayerPresenter onAdsStopBuffering")
    }

    override fun onStartPlayAds(p0: Advertis?, p1: Int) {
        LogUtil.d(BaseApplication.TestToken,"PlayerPresenter onStartPlayAds")
    }

    override fun onCompletePlayAds() {
        LogUtil.d(BaseApplication.TestToken,"PlayerPresenter onCompletePlayAds")
    }

    override fun onError(what: Int, extra: Int) {
        LogUtil.d(BaseApplication.TestToken,"PlayerPresenter onError,the what args is $what and the extra arg is $extra")
    }
    //end of the ad's callbacks

    //the start of the player's callbacks
    override fun onPlayStart() {
        mPlayerManager.play()
    }

    override fun onPlayPause() {
        LogUtil.d(BaseApplication.TestToken,"onPlayPause")
    }

    override fun onPlayStop() {
        LogUtil.d(BaseApplication.TestToken,"onPlayStop")
    }

    override fun onSoundPlayComplete() {
        LogUtil.d(BaseApplication.TestToken,"onSoundPlayComplete")
    }

    override fun onSoundPrepared() {
        LogUtil.d(BaseApplication.TestToken,"onSoundPrepared")
    }

    override fun onSoundSwitch(p0: PlayableModel?, p1: PlayableModel?) {
        LogUtil.d(BaseApplication.TestToken,"onSoundSwitch")
    }

    override fun onBufferingStart() {
        LogUtil.d(BaseApplication.TestToken,"onBufferingStart")
    }

    override fun onBufferingStop() {
        LogUtil.d(BaseApplication.TestToken,"onBufferingStop")
    }

    override fun onBufferProgress(percent: Int) {
        LogUtil.d(BaseApplication.TestToken,"onBufferProgress..$percent")

    }

    override fun onPlayProgress(current: Int, duration: Int) {
        LogUtil.d(BaseApplication.TestToken,"onPlayProgress")
    }

    override fun onError(p0: XmPlayerException?): Boolean {
        LogUtil.d(BaseApplication.TestToken,"onError $p0")
        return false
    }
    //end of the player's callbacks

}