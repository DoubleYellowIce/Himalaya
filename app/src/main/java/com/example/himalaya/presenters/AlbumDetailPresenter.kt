package com.example.himalaya.presenters


import com.example.himalaya.base.BaseApplication
import com.example.himalaya.interfaces.IAlbumDetailPresenter
import com.example.himalaya.interfaces.IAlbumDetailViewCallback
import com.example.himalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.model.track.TrackList
import java.util.*
import kotlin.collections.HashMap

 /**
  * @Author DoubleYellowIce
  * @Date 2021/11/25
  */


class AlbumDetailPresenter private constructor():IAlbumDetailPresenter {

    var mTargetAlbum:Album?=null

    private var mAlbumDetailViewCallbacks=LinkedList<IAlbumDetailViewCallback>()

    companion object{
        val albumDetailPresenter=AlbumDetailPresenter()
    }

    override fun pull2RefreshMore() {
        TODO("Not yet implemented")
    }

    override fun loadMore() {
        TODO("Not yet implemented")
    }

    override fun getAlbumDetail(albumId: Int, page: Int) {
        val map=HashMap<String,String>()
        map[DTransferConstants.ALBUM_ID]=albumId.toString()
        map[DTransferConstants.SORT]="asc"
        map[DTransferConstants.PAGE]=page.toString()
        map[DTransferConstants.PAGE_SIZE]=BaseApplication.per_page_track_count
        CommonRequest.getTracks(map,object :IDataCallBack<TrackList>{
            override fun onSuccess(trackList: TrackList?) {
                val tracks=trackList?.tracks
                if (tracks!=null) {
                    handleAlbumDetailResult(tracks)
                }
            }

            override fun onError(errCode: Int, errMes: String?) {
                LogUtil.d(BaseApplication.TestToken,"CommonRequest.getTracks:the errCode is ${errCode},the errMessage is ${errMes}")
            }
        })

    }

     private fun handleAlbumDetailResult(tracks: List<Track>) {
        for (mAlbumDetailCallback in mAlbumDetailViewCallbacks){
            mAlbumDetailCallback.onDetailListLoaded(tracks)
        }
     }

     override fun registerViewCallback(iAlbumDetailViewCallback: IAlbumDetailViewCallback) {
        if (!mAlbumDetailViewCallbacks.contains(iAlbumDetailViewCallback)){
            mAlbumDetailViewCallbacks.add(iAlbumDetailViewCallback)
            if (mTargetAlbum!=null){
                iAlbumDetailViewCallback.onAlbumLoaded(mTargetAlbum!!)
            }
        }
    }

    override fun unregisterViewCallback(iAlbumDetailViewCallback: IAlbumDetailViewCallback) {
        if (mAlbumDetailViewCallbacks.contains(iAlbumDetailViewCallback)){
            mAlbumDetailViewCallbacks.remove(iAlbumDetailViewCallback)
        }
    }

    fun setTargetAlbum(album: Album){
        this.mTargetAlbum=album
    }
}