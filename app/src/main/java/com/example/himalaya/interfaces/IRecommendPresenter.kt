package com.example.himalaya.interfaces

interface IRecommendPresenter {

    fun getRecommendList()

    fun registerCallback(iRecommendViewCallback: IRecommendViewCallback)

    fun unregisterCallback(iRecommendViewCallback: IRecommendViewCallback)



}