package com.example.himalaya

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.himalaya.adapters.DetailListAdapter
import com.example.himalaya.base.BaseActivity
import com.example.himalaya.base.BaseApplication
import com.example.himalaya.interfaces.IAlbumDetailViewCallback
import com.example.himalaya.presenters.AlbumDetailPresenter
import com.example.himalaya.presenters.PlayerPresenter
import com.example.himalaya.utils.LogUtil
import com.example.himalaya.views.ImageBlur
import com.example.himalaya.views.UILoader
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.Track
import net.lucode.hackware.magicindicator.buildins.UIUtil
import java.lang.Exception

class DetailActivity:BaseActivity(), IAlbumDetailViewCallback,UILoader.OnRetryClickListener,DetailListAdapter.ItemClickListener {

    private var mCurrentPage=1

    private var mCurrentId=-1

    private lateinit var mLargeCover:ImageView

    private lateinit var mSmallCover:ImageView

    private lateinit var mAlbumTitle:TextView

    private lateinit var mAlbumAuthor:TextView

    private lateinit var mAlbumDetailPresenter: AlbumDetailPresenter

    private lateinit var mDetailListRV:RecyclerView

    private lateinit var detailListAdapter:DetailListAdapter

    private lateinit var mDetailListContainer:FrameLayout

    private var mUiLoader:UILoader?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        initView()
        mAlbumDetailPresenter= AlbumDetailPresenter.albumDetailPresenter
        //after registered in the mAlbumDetailPresenter
        //mAlbumDetailPresenter will automatically callback the onAlbumLoaded() function
        //so there is no need to use mAlbumDetailPresenter's getAlbumDetail directly
        mAlbumDetailPresenter.registerViewCallback(this)

    }

    private fun initView() {
        mDetailListContainer=findViewById(R.id.detail_list_container)

        if (mUiLoader==null){
            mUiLoader=object:UILoader(this){
                override fun getSuccessView(container: ViewGroup): View {
                    return createSuccessView(container)
                }
            }
            mUiLoader!!.addRetryClickListener(this)
            mDetailListContainer.removeAllViews()
            mDetailListContainer.addView(mUiLoader)

        }
        mLargeCover=findViewById(R.id.iv_large_cover)
        mSmallCover=findViewById(R.id.iv_small_cover)
        mAlbumTitle=findViewById(R.id.iv_album_title)
        mAlbumAuthor=findViewById(R.id.iv_album_author)

    }

    private fun createSuccessView(container: ViewGroup): View {
        val detailView=LayoutInflater.from(this).inflate(R.layout.item_detail_list,container,false)
        val layoutManager=LinearLayoutManager(this)
        detailListAdapter=DetailListAdapter().apply {
            setItemClickListener(this@DetailActivity)
        }
        mDetailListRV=detailView.findViewById<RecyclerView?>(R.id.album_detail_list).apply {
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
        return detailView
    }

    override fun onDetailListLoaded(tracks: List<Track>) {
        //when albumDetailPresenter get the tracks in details,this function will be called
        if (tracks.isEmpty()){
            LogUtil.d(BaseApplication.TestToken,"DetailActivity onDetailListLoaded() isEmpty")
            mUiLoader!!.updateStatus(UILoader.UIStatus.EMPTY)
        }else {
            LogUtil.d(BaseApplication.TestToken,"DetailActivity onDetailListLoaded() success")
            mUiLoader!!.updateStatus(UILoader.UIStatus.SUCCESS)
        }
        detailListAdapter.setData(tracks)
    }

    override fun onAlbumLoaded(album: Album) {
        LogUtil.d(BaseApplication.TestToken,"DetailActivity onAlbumLoaded")
        mUiLoader!!.updateStatus(UILoader.UIStatus.LOADING)
        mCurrentId=album.id.toInt()
        mAlbumDetailPresenter.getAlbumDetail(mCurrentId,mCurrentPage)
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

    override fun onNetworkError() {
        mUiLoader!!.updateStatus(UILoader.UIStatus.NETWORK_ERROR)
    }

    override fun onRetryClick() {
        mUiLoader!!.updateStatus(UILoader.UIStatus.LOADING)
        mAlbumDetailPresenter.getAlbumDetail(mCurrentId,mCurrentPage)
    }

    override fun onItemClick(mDetailData: List<Track>, position: Int) {
        //
        PlayerPresenter.playerPresenter.setPlayList(mDetailData,position)
        val intent=Intent(this,PlayerActivity::class.java)
        startActivity(intent)
    }
}