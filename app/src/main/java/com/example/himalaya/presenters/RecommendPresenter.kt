package com.example.himalaya.presenters

import com.example.himalaya.base.BaseApplication
import com.example.himalaya.interfaces.IRecommendPresenter
import com.example.himalaya.interfaces.IRecommendViewCallback
import com.example.himalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList
import java.util.*
import kotlin.collections.HashMap

object RecommendPresenter :IRecommendPresenter{

    private val mRecommendViewCallbacks=LinkedList<IRecommendViewCallback>()

    override fun getRecommendList() {
        onLoading()
        val map=HashMap<String,String>()
        map[DTransferConstants.LIKE_COUNT] = BaseApplication.recommend_count
        CommonRequest.getGuessLikeAlbum(map,object: IDataCallBack<GussLikeAlbumList> {
            override fun onSuccess(p0: GussLikeAlbumList?) {
                LogUtil.d(BaseApplication.TestToken,"the status of getGuessLikeAlbum is onSuccess")
                val albums=p0?.albumList
                if (albums!=null){
                    handlerRecommendResult(albums)
                }
            }
            override fun onError(p0: Int, p1: String?) {
                LogUtil.d(BaseApplication.TestToken,"the status of getGuessLikeAlbum is on error")
                handleError(p0,p1)
            }

        })    }

    override fun registerViewCallback(t: IRecommendViewCallback) {
        if (!mRecommendViewCallbacks.contains(t)){
            mRecommendViewCallbacks.add(t)
        }
    }

    override fun unregisterViewCallback(t: IRecommendViewCallback) {
        if (mRecommendViewCallbacks.contains(t)){
            mRecommendViewCallbacks.remove(t)
        }    }

    private fun handleError(p0: Int, p1: String?) {
        for (callback in mRecommendViewCallbacks){
            callback.onError()
        }
    }

    private fun onLoading(){
        for (callback in mRecommendViewCallbacks){
            callback.onLoading()
        }
    }

    private fun handlerRecommendResult(albums: List<Album>) {
        if(albums.isEmpty()){
            for (callback in mRecommendViewCallbacks){
                callback.onEmpty()
            }
        }else{
            for (callback in mRecommendViewCallbacks){
                callback.onRecommendListLoaded(albums)
            }
        }
    }



}