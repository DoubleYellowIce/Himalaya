package com.example.himalaya.interfaces

import com.example.himalaya.base.IBasePresenter

interface IAlbumDetailPresenter:IBasePresenter<IAlbumDetailViewCallback> {

    fun pull2RefreshMore()

    fun loadMore()

    fun getAlbumDetail(albumId:Int,page:Int)

}