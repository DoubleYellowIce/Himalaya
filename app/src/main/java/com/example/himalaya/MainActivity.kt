package com.example.himalaya


import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.example.himalaya.adapters.IndicatorAdapter
import com.example.himalaya.adapters.MainContentAdapter
import io.reactivex.rxjava3.core.Observable
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import java.util.*

/**
 * @Author DoubleYellowIce
 * @Date 2021/11/29
 */


class MainActivity : FragmentActivity() {

    /**
     * mMagicIndicator indicates the current fragment
     */
    private lateinit var mMagicIndicator: MagicIndicator


    /**
     * commonNavigator's navigator which helps switch back and forth between the fragment
     */
    private lateinit var commonNavigator: CommonNavigator

    /**
     *mContentPager manage the fragments
     */
    private lateinit var mContentPager: ViewPager

    /**
     * mainContentAdapter is the mContentPager's adapter,which helps creates the fragment indirectly
     */
    private lateinit var mainContentAdapter: MainContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        val option=BitmapFactory.Options()
//        option.inJustDecodeBounds=true
//        val bitmap=BitmapFactory.decodeResource(resources,R.drawable.ic_track_play,option)
//        val width=option.outWidth
//        val height=option.outHeight

    }

    private fun initView(){

        mainContentAdapter= MainContentAdapter(supportFragmentManager)
        mContentPager=findViewById(R.id.view_pager)
        mContentPager.adapter=mainContentAdapter

        commonNavigator= CommonNavigator(this).apply {
            /**
             * 
             */
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



