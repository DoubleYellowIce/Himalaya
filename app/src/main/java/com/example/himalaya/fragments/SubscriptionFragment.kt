package com.example.himalaya.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.himalaya.R
import com.example.himalaya.base.BaseFragment

class SubscriptionFragment:BaseFragment() {

    private lateinit var rootView: View
    override fun onSubViewLoaded(layoutInflater: LayoutInflater,container: ViewGroup): View {
        rootView=layoutInflater.inflate(R.layout.fragment_recommend,container,false)
        return rootView
    }

}