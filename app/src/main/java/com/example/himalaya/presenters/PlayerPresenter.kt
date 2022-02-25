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
import java.util.*

class PlayerPresenter private constructor():IPlayerPresenter, IXmAdsStatusListener,
    IXmPlayerStatusListener {

    private var isPlayListSet=false


    private val mPlayerCallbacks =LinkedList<IPlayerCallback>()

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

    //提供给mPlayerCallbacks的回调 start
    override fun play() {
        if (isPlayListSet){
            mPlayerManager.play()
            for (cb in mPlayerCallbacks){
                cb.onPlayStart()
            }
        }
    }

    override fun pause() {
        mPlayerManager.pause()
        for (cb in mPlayerCallbacks){
            cb.onPlayPause()
        }
    }

    override fun stop() {
        mPlayerManager.stop()
        for (cb in mPlayerCallbacks){
            cb.onPlayStop()
        }
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

    override fun isPlaying(): Boolean {
        return mPlayerManager.isPlaying
    }
    //提供给mPlayerCallbacks的回调 start

    override fun registerViewCallback(t: IPlayerCallback) {
        TODO("Not yet implemented")
    }

    override fun unregisterViewCallback(t: IPlayerCallback) {
        TODO("Not yet implemented")
    }

    //喜马拉雅广告的回调 start
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
    //喜马拉雅广告的回调 end

    //喜马拉雅播放器的回调接口 start
    override fun onPlayStart() {

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
        for (cb in mPlayerCallbacks){
            cb.onProgressChange(current,duration)
        }
        LogUtil.d(BaseApplication.TestToken,"onPlayProgress(),current is $current")
    }

    override fun onError(p0: XmPlayerException?): Boolean {
        LogUtil.d(BaseApplication.TestToken,"onError $p0")
        return false
    }
    //喜马拉雅播放器的回调接口 end

    fun registerCallback(iPlayerCallback: IPlayerCallback) {
        mPlayerCallbacks.add(iPlayerCallback)
    }

    fun unregisterCallback(iPlayerCallback: IPlayerCallback){
        if (mPlayerCallbacks.contains(iPlayerCallback)){
            mPlayerCallbacks.remove(iPlayerCallback);
        }
    }
}