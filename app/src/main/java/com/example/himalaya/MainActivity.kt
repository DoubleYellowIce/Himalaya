package com.example.himalaya


import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.example.himalaya.adapters.IndicatorAdapter
import com.example.himalaya.adapters.MainContentAdapter
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator



class MainActivity : FragmentActivity() {

    private lateinit var mMagicIndicator: MagicIndicator
    private lateinit var commonNavigator: CommonNavigator
    private lateinit var mContentPager: ViewPager
    private lateinit var mainContentAdapter: MainContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){

        mainContentAdapter= MainContentAdapter(supportFragmentManager)
        mContentPager=findViewById(R.id.view_pager)
        mContentPager.adapter=mainContentAdapter

        commonNavigator= CommonNavigator(this).apply {
            adapter=IndicatorAdapter(mContentPager)
            isAdjustMode=true

        }

        mMagicIndicator=findViewById<MagicIndicator?>(R.id.magic_indicator).apply {
            navigator=commonNavigator
            setBackgroundColor(this@MainActivity.resources.getColor(R.color.main_color))
        }

        ViewPagerHelper.bind(mMagicIndicator, mContentPager)
    }



}