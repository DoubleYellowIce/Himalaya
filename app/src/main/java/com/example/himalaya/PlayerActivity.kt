package com.example.himalaya

import android.os.Bundle
import com.example.himalaya.base.BaseActivity
import com.example.himalaya.interfaces.IPlayerCallback
import com.example.himalaya.presenters.PlayerPresenter
import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl

class PlayerActivity : BaseActivity(),IPlayerCallback {
    private var mPlayerPresenter:PlayerPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        mPlayerPresenter=PlayerPresenter.playerPresenter
        //before the creation of this activity
        //the playlist has already been set in the playerPresenter
        //if you want to get more information,please check the itemClick() in DetailListAdapter()
        mPlayerPresenter?.play()
        initView()
        initEvent()
    }

    fun initView(){

    }

    fun initEvent(){

    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayerPresenter?.stop()
        mPlayerPresenter=null
    }
    override fun onPlayStart() {
        TODO("Not yet implemented")
    }

    override fun onPlayPause() {
        TODO("Not yet implemented")
    }

    override fun onPlayStop() {
        TODO("Not yet implemented")
    }

    override fun onPlayError() {
        TODO("Not yet implemented")
    }

    override fun nextPlay(track: Track) {
        TODO("Not yet implemented")
    }

    override fun onPrePlay(track: Track) {
        TODO("Not yet implemented")
    }

    override fun onListLoaded(list: List<Track>) {
        TODO("Not yet implemented")
    }

    override fun onPlayModeChange(playMode: XmPlayListControl.PlayMode) {
        TODO("Not yet implemented")
    }

    override fun onProgressChange(currentIndex: Long, total: Long) {
        TODO("Not yet implemented")
    }

    override fun onAdLoading() {
        TODO("Not yet implemented")
    }

    override fun onAdFinished() {
        TODO("Not yet implemented")
    }
}