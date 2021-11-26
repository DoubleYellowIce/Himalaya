package com.example.himalaya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.himalaya.R
import com.example.himalaya.base.BaseFragment

class HistoryFragment:BaseFragment() {

        /**
         * @Author DoubleYellowIce
         * @Date 2021/11/12
         * @Since version-1.0
         */
        

    

    private lateinit var rootView: View

    /**
     * @Author DoubleYellowIce
     * @Date 2021/11/12
     * @Description This is description of method
     * @Param
     * @Return
     * @Since version-1.0
     */


    override fun onSubViewLoaded(layoutInflater: LayoutInflater,container: ViewGroup): View {
        rootView=layoutInflater.inflate(R.layout.fragment_recommend,container,false)
        return rootView
    }

}