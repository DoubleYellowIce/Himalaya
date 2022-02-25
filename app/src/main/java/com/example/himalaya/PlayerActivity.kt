package com.example.himalaya

import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.example.himalaya.base.BaseActivity
import com.example.himalaya.interfaces.IPlayerCallback
import com.example.himalaya.presenters.PlayerPresenter
import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl
import java.text.SimpleDateFormat

class PlayerActivity : BaseActivity(),IPlayerCallback {

    private var mPlayerPresenter:PlayerPresenter = PlayerPresenter.playerPresenter

    private var mPlayerControlView:ImageView?=null

    private var mSeekBar:SeekBar?=null

    private var cPosition:TextView?=null

    private var duration:TextView?=null

    private val mDurationFormat= SimpleDateFormat("mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initView()
        initEvent()
        //before the creation of this activity
        //the playlist has already been set in the playerPresenter
        //if you want to get more information,please check the itemClick() in DetailListAdapter()
        mPlayerPresenter.play()

    }

    private fun initView(){
        mPlayerControlView=findViewById(R.id.play_control_view)
        mSeekBar=findViewById(R.id.current_dot)
        cPosition=findViewById(R.id.current_position)
        duration=findViewById(R.id.track_duration)

    }

    private fun initEvent(){
        mPlayerPresenter.registerCallback(this)
        mPlayerControlView?.setOnClickListener(){
            if (mPlayerPresenter.isPlaying()){
                mPlayerPresenter.pause()
            }else{
                mPlayerPresenter.play()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayerPresenter.unregisterCallback(this)
        mPlayerPresenter.stop()
    }

    override fun onPlayStart() {
        mPlayerControlView?.setImageResource(R.drawable.ic_track_pause)
    }

    override fun onPlayPause() {
        mPlayerControlView?.setImageResource(R.drawable.ic_track_play)
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

    override fun onProgressChange(currentIndex: Int, total: Int) {
        mSeekBar?.progress=(currentIndex*100/total)
        cPosition?.text=mDurationFormat.format(currentIndex)
        duration?.text=mDurationFormat.format(total);
    }

    override fun onAdLoading() {
        TODO("Not yet implemented")
    }

    override fun onAdFinished() {
        TODO("Not yet implemented")
    }
}