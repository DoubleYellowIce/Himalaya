package com.example.himalaya

import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.himalaya.base.BaseActivity
import com.example.himalaya.base.BaseApplication
import com.example.himalaya.interfaces.IAlbumDetailViewCallback
import com.example.himalaya.presenters.AlbumDetailPresenter
import com.example.himalaya.utils.LogUtil
import com.example.himalaya.views.ImageBlur
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.Track
import java.lang.Exception

class DetailActivity:BaseActivity(), IAlbumDetailViewCallback {

    private var mCurrentPage=1

    private lateinit var mLargeCover:ImageView

    private lateinit var mSmallCover:ImageView

    private lateinit var mAlbumTitle:TextView

    private lateinit var mAlbumAuthor:TextView

    private lateinit var mAlbumDetailPresenter: AlbumDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
//        window.statusBarColor=Color.TRANSPARENT
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        initView()
        mAlbumDetailPresenter= AlbumDetailPresenter.albumDetailPresenter
        mAlbumDetailPresenter.registerViewCallback(this)

    }

    private fun initView() {
        mLargeCover=findViewById(R.id.iv_large_cover)
        mSmallCover=findViewById(R.id.iv_small_cover)
        mAlbumTitle=findViewById(R.id.iv_album_title)
        mAlbumAuthor=findViewById(R.id.iv_album_author)
    }

    override fun onDetailListLoaded(tracks: List<Track>) {
        TODO("Not yet implemented")
    }

    override fun onAlbumLoaded(album: Album) {
        mAlbumDetailPresenter.getAlbumDetail(album.id.toInt(),mCurrentPage)
        mAlbumTitle.text=album.albumTitle
        mAlbumAuthor.text=album.announcer.nickname
        Picasso.get().load(album.coverUrlLarge).into(mLargeCover,object:Callback{
            override fun onSuccess() {
                ImageBlur.makeBlur(mLargeCover,this@DetailActivity)
            }
            override fun onError(e: Exception?) {
                LogUtil.d(BaseApplication.TestToken,"Picasso can't not load the large cover URL")
            }

        })
        Picasso.get().load(album.coverUrlSmall).into(mSmallCover)

    }
}