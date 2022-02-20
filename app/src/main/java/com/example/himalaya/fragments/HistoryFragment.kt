package com.example.himalaya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.himalaya.R
import com.example.himalaya.base.BaseFragment

class HistoryFragment:BaseFragment() {

    private lateinit var rootView: View

    override fun onSubViewLoaded(layoutInflater: LayoutInflater,container: ViewGroup): View {
        rootView=layoutInflater.inflate(R.layout.fragment_recommend,container,false)
        return rootView
    }

}