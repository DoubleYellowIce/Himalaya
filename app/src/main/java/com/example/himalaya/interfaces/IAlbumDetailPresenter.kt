package com.example.himalaya.interfaces

interface IAlbumDetailPresenter {

    fun pull2RefreshMore()

    fun loadMore()

    fun getAlbumDetail(albumId:Int,page:Int)

    fun registerViewCallback(iAlbumDetailViewCallback: IAlbumDetailViewCallback)

    fun unregisterViewCallback(iAlbumDetailViewCallback: IAlbumDetailViewCallback)
}