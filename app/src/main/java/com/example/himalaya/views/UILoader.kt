package com.example.himalaya.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.himalaya.R
import com.example.himalaya.base.BaseApplication
import com.example.himalaya.utils.LogUtil

abstract class UILoader:FrameLayout{

    enum class UIStatus{
        LOADING,SUCCESS,NETWORK_ERROR,EMPTY,NONE
    }

    interface OnRetryClickListener{
        fun onRetryClick()
    }

    constructor(context: Context):super(context)

    constructor(context: Context,attrs:AttributeSet):super(context,attrs)

    constructor(context: Context,attrs:AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr)

    private var mSuccessView:View?=null

    private var mNetworkErrorView :View?=null

    private var mLoadingView:View?=null

    private var mEmptyView:View?=null

    private lateinit var retryClickListener:OnRetryClickListener

    private var mCurrentStatus:UIStatus=UIStatus.NONE

    private fun switchUIByCurrentStatus() {

            if (mLoadingView==null) {
                mLoadingView = getLoadingView()
                addView(mLoadingView)
            }
            mLoadingView!!.visibility=if(mCurrentStatus==UIStatus.LOADING) VISIBLE else GONE
//            LogUtil.d(BaseApplication.TestToken,"mLoadingView's visibility is ${mLoadingView!!.visibility}")

            if (mSuccessView==null){
                mSuccessView=getSuccessView(this)
                addView(mSuccessView)
            }
            mSuccessView!!.visibility=if (mCurrentStatus==UIStatus.SUCCESS) VISIBLE else GONE

            if (mNetworkErrorView==null){
                mNetworkErrorView=getErrorView()
                addView(mNetworkErrorView)
            }
            mNetworkErrorView!!.visibility=if (mCurrentStatus==UIStatus.NETWORK_ERROR) VISIBLE else GONE

            if (mEmptyView==null){
                mEmptyView=getEmptyView()
                addView(mEmptyView)
            }
            mEmptyView!!.visibility=if(mCurrentStatus==UIStatus.EMPTY) VISIBLE else GONE

    }



    private fun getEmptyView(): View {
        val onEmptyView=LayoutInflater.from(context).inflate(R.layout.fragment_empty_view,this,false).apply {
            setOnClickListener{
                LogUtil.d(BaseApplication.TestToken, "onEmptyView onRetryClick")
                retryClickListener.onRetryClick()
            }
        }
        return onEmptyView
    }

    private fun getErrorView(): View {
        val onErrorView=LayoutInflater.from(context).inflate(R.layout.fragment_error_view,this,false).apply {
            setOnClickListener{
                LogUtil.d(BaseApplication.TestToken, "onErrorView onRetryClick")
                retryClickListener.onRetryClick()
            }
        }
        return onErrorView
    }

    private fun getLoadingView(): View {
        return LayoutInflater.from(context).inflate(R.layout.fragment_loading_view,this,false)
    }

    abstract fun getSuccessView(container:ViewGroup): View

    fun addRetryClickListener(onRetryClickListener: OnRetryClickListener){
        this.retryClickListener=onRetryClickListener
    }

    fun updateUI() {
        LogUtil.d(BaseApplication.TestToken,"UILoader updateUI() the current status is $mCurrentStatus")
        BaseApplication.sHandler.post{
            switchUIByCurrentStatus()
        }
    }

    fun updateStatus(status:UIStatus){
        mCurrentStatus=status
        updateUI()
    }
}