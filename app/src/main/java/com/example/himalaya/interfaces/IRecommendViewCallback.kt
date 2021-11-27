package com.example.himalaya.interfaces

import com.ximalaya.ting.android.opensdk.model.album.Album

interface IRecommendViewCallback {
     /**
      * @Author DoubleYellowIce
      * @Date 2021/11/12
      * @Since version-1.0
      */

    fun onRecommendListLoaded(albums:List<Album>)

    fun onLoading()

    fun onError()

    fun onEmpty()

}