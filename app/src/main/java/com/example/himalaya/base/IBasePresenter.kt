package com.example.himalaya.base


interface IBasePresenter<T> {
    fun registerViewCallback(t:T)

    fun unregisterViewCallback(t:T)
}