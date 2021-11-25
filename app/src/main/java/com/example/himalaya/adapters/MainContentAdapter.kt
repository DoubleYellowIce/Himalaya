package com.example.himalaya.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.himalaya.utils.FragmentCreator

class MainContentAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return FragmentCreator.PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
    return FragmentCreator.getFragment(position)
    }
}