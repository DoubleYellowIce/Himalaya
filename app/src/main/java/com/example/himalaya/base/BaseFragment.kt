package com.example.himalaya.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment:Fragment() {
    private lateinit var rootView:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView=onSubViewLoaded(layoutInflater,container!!)
        return rootView
    }

    abstract fun onSubViewLoaded(layoutInflater: LayoutInflater,container:ViewGroup): View
}