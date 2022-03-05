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

    private var mPlayerControlView:ImageView

    private var mPlayPre:ImageView

    private var mPlayNext:ImageView

    private var mSeekBar:SeekBar

    private var cPosition:TextView

    private var duration:TextView

    private var mTrackTitle:TextView

    //当集时长小于一小时时，使用分钟格式
    private val mMinFormat= SimpleDateFormat("mm:ss")

    private val mHourFormat= SimpleDateFormat("hh:mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initEvent()
        //before the creation of this activity
        //the playlist has already been set in the playerPresenter
        //if you want to get more information,please check the itemClick() in DetailListAdapter()
        PlayerPresenter.play()

    }

    init {
        mPlayerControlView=findViewById(R.id.play_control_view)
        mSeekBar=findViewById(R.id.current_dot)
        cPosition=findViewById(R.id.current_position)
        duration=findViewById(R.id.track_duration)
        mTrackTitle=findViewById(R.id.track_title)
        mPlayPre=findViewById(R.id.play_pre)
        mPlayNext=findViewById(R.id.play_next)
    }

    private fun initEvent(){
        PlayerPresenter.registerCallback(this)
        mPlayerControlView?.setOnClickListener(){
            if (PlayerPresenter.isPlaying()){
                PlayerPresenter.pause()
            }else{
                PlayerPresenter.play()
            }
        }
        mSeekBar.apply {
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (seekBar!=null){
                        PlayerPresenter.seekTo(seekBar.progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        PlayerPresenter.unregisterCallback(this)
        PlayerPresenter.stop()
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
        //通过设置seekbar的最大值来让其自动计算进度
        mSeekBar?.max=total
        if(total>=60*60*1000){
            cPosition?.text=mHourFormat.format(currentIndex)
            duration?.text=mHourFormat.format(total)
        }else{
            cPosition?.text=mMinFormat.format(currentIndex)
            duration?.text=mMinFormat.format(total)
        }
        mSeekBar?.progress = currentIndex
    }

    override fun onAdLoading() {
        TODO("Not yet implemented")
    }

    override fun onAdFinished() {
        TODO("Not yet implemented")
    }
}