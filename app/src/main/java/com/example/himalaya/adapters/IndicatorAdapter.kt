package com.example.himalaya.adapters

import android.content.Context
import android.graphics.Color
import androidx.viewpager.widget.ViewPager
import com.example.himalaya.base.BaseApplication
import com.example.himalaya.utils.LogUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

class IndicatorAdapter(private val viewPager: ViewPager):CommonNavigatorAdapter() {

    private val mTitleDataList= listOf("推荐","订阅","历史")

    override fun getCount(): Int {
        return mTitleDataList.size
    }

    override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
        val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
        colorTransitionPagerTitleView.normalColor = Color.GRAY
        colorTransitionPagerTitleView.selectedColor = Color.BLACK
        colorTransitionPagerTitleView.text = mTitleDataList[index]
        colorTransitionPagerTitleView.setOnClickListener {
            LogUtil.d(BaseApplication.TestToken,"colorTransitionPagerTitleView.setOnClickListener")
            viewPager.currentItem=index
        }
        return colorTransitionPagerTitleView
    }

    override fun getIndicator(context: Context?): IPagerIndicator {
        val indicator = LinePagerIndicator(context)
        indicator.mode = LinePagerIndicator.FOCUSABLES_TOUCH_MODE
        return indicator
    }


}