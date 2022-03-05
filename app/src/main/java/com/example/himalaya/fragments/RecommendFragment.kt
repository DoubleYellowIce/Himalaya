package com.example.himalaya.fragments

import android.content.Intent
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.himalaya.DetailActivity
import com.example.himalaya.R
import com.example.himalaya.adapters.RecommendListAdapter
import com.example.himalaya.base.BaseApplication
import com.example.himalaya.base.BaseFragment
import com.example.himalaya.interfaces.IRecommendViewCallback
import com.example.himalaya.presenters.AlbumDetailPresenter
import com.example.himalaya.presenters.RecommendPresenter
import com.example.himalaya.utils.LogUtil
import com.example.himalaya.views.UILoader
import com.ximalaya.ting.android.opensdk.model.album.Album
import net.lucode.hackware.magicindicator.buildins.UIUtil

 /**
  * @Author DoubleYellowIce
  * @Date 2021/11/16
  */

class RecommendFragment: BaseFragment(),IRecommendViewCallback,UILoader.OnRetryClickListener,
     RecommendListAdapter.OnRecommendItemClickListener {

    private lateinit var rootView: View

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mRecommendAdapter:RecommendListAdapter

    private lateinit var mUILoader: UILoader

    private lateinit var albums: List<Album>

    override fun onSubViewLoaded(layoutInflater: LayoutInflater,container: ViewGroup): View {
        LogUtil.d(BaseApplication.TestToken,"RecommendFragment onSubViewLoaded()")
        mRecommendAdapter= RecommendListAdapter().apply {
            setItemClickListener(this@RecommendFragment)
        }

        mUILoader=object :UILoader(requireContext()){
            //this UILoader will show the content depend on whether the system get the recommended List successfully or not
            //besides the successView, there are also the emptyView,errorView and loadingView
            //but only the successView is changeable, so getSuccessView() needed to be override
            override fun getSuccessView(container: ViewGroup): View {
                return createSuccessView(layoutInflater,container)
            }
        }.apply { addRetryClickListener(this@RecommendFragment) }


        RecommendPresenter.apply {
            registerViewCallback(this@RecommendFragment)
            getRecommendList()
        }


        if (mUILoader.parent is ViewGroup){
            (mUILoader.parent as ViewGroup).removeView(mUILoader)
        }

        return mUILoader
    }

    private fun createSuccessView(layoutInflater: LayoutInflater,container: ViewGroup): View {
        LogUtil.d(BaseApplication.TestToken,"RecommendFragment createSuccessView()")
        rootView=layoutInflater.inflate(R.layout.fragment_recommend,container,false)
        val linearLayoutManager=LinearLayoutManager(context).apply {
            orientation=LinearLayoutManager.VERTICAL
        }
        mRecyclerView=rootView.findViewById<RecyclerView?>(R.id.recommend_recycler_view).apply {
            adapter=mRecommendAdapter
            layoutManager=linearLayoutManager
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top=UIUtil.dip2px(view.context, 5.0)
                    outRect.bottom=UIUtil.dip2px(view.context, 5.0)
                    outRect.left=UIUtil.dip2px(view.context, 5.0)
                    outRect.right=UIUtil.dip2px(view.context, 5.0)
                }
            })
        }

        return rootView

    }

    override fun onRecommendListLoaded(albums: List<Album>) {
        LogUtil.d(BaseApplication.TestToken,"RecommendFragment onRecommendListLoaded()")
        this.albums=albums
        mRecommendAdapter.setData(albums)
        mRecommendAdapter.notifyDataSetChanged()
        mUILoader.updateStatus(UILoader.UIStatus.SUCCESS)
    }

    override fun onLoading() {
        LogUtil.d(BaseApplication.TestToken,"RecommendFragment onLoading()")
        mUILoader.updateStatus(UILoader.UIStatus.LOADING)
    }

    override fun onError() {
        LogUtil.d(BaseApplication.TestToken,"RecommendFragment onError()")
        mUILoader.updateStatus(UILoader.UIStatus.NETWORK_ERROR)
    }

    override fun onEmpty() {
        LogUtil.d(BaseApplication.TestToken,"RecommendFragment onEmpty()")
        mUILoader.updateStatus(UILoader.UIStatus.EMPTY)
    }

    override fun onDestroyView() {
        LogUtil.d(BaseApplication.TestToken,"RecommendFragment onDestroyView()")
        super.onDestroyView()
        RecommendPresenter.unregisterViewCallback(this)
    }

     override fun onRetryClick() {
         RecommendPresenter.getRecommendList()
     }

     override fun onItemClick(album:Album) {
         //set the albumDetailPresenter's mTargetAlbum to the album which is clicked
         //so after the DetailActivity has register in albumDetailPresenter
         //the albumDetailPresenter will transfer this album as parameter to the onAlbumLoaded() function in DetailActivity
         AlbumDetailPresenter.setTargetAlbum(album)
         val intent=Intent(context,DetailActivity::class.java)
         startActivity(intent)
     }

 }