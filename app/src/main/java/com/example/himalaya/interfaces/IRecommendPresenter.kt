package com.example.himalaya.interfaces

import com.example.himalaya.base.IBasePresenter

interface IRecommendPresenter:IBasePresenter<IRecommendViewCallback> {

    fun getRecommendList()

}