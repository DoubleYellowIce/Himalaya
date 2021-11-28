package com.example.himalaya

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.provider.ContactsContract
import android.util.LayoutDirection
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.himalaya.adapters.DetailListAdapter
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
import net.lucode.hackware.magicindicator.buildins.UIUtil
import java.lang.Exception

class DetailActivity:BaseActivity(), IAlbumDetailViewCallback {

    private var mCurrentPage=1

    private lateinit var mLargeCover:ImageView

    private lateinit var mSmallCover:ImageView

    private lateinit var mAlbumTitle:TextView

    private lateinit var mAlbumAuthor:TextView

    private lateinit var mAlbumDetailPresenter: AlbumDetailPresenter

    private lateinit var mAlbumDetailList:RecyclerView

    private lateinit var detailListAdapter:DetailListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
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
        val layoutManager=LinearLayoutManager(this)
        detailListAdapter=DetailListAdapter()
        mAlbumDetailList=findViewById<RecyclerView?>(R.id.album_detail_list).apply {
            setLayoutManager(layoutManager)
            adapter=detailListAdapter
            addItemDecoration(object :RecyclerView.ItemDecoration(){
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top= UIUtil.dip2px(view.context, 2.0)
                    outRect.bottom= UIUtil.dip2px(view.context, 2.0)
                    outRect.left= UIUtil.dip2px(view.context, 2.0)
                    outRect.right= UIUtil.dip2px(view.context, 2.0)
                }
            })
        }
    }

    override fun onDetailListLoaded(tracks: List<Track>) {
        //to display the information of the album's tracks
        detailListAdapter.setData(tracks)

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