package com.example.himalaya.utils

import android.view.LayoutInflater
import com.example.himalaya.base.BaseFragment
import com.example.himalaya.fragments.HistoryFragment
import com.example.himalaya.fragments.RecommendFragment
import com.example.himalaya.fragments.SubscriptionFragment

object FragmentCreator {

    private val sCache= HashMap<Int,BaseFragment>()
    private val INDEX_RECOMMEND=0
    private val INDEX_HISTORY=1
    private val INDEX_SUBSCRIPTION=2
    val PAGE_COUNT=3

    fun getFragment(index:Int): BaseFragment {
        var baseFragment=sCache[index]
        if (baseFragment==null){
            when(index){
                INDEX_HISTORY->
                    baseFragment=HistoryFragment()
                INDEX_RECOMMEND->
                    baseFragment=RecommendFragment()
                INDEX_SUBSCRIPTION->
                    baseFragment=SubscriptionFragment()
            }
            sCache[index] = baseFragment!!
        }
        return baseFragment
    }
}