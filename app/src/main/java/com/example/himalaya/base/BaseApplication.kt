package com.example.himalaya.base

import android.app.Application
import android.content.Context
import android.os.Handler
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.DeviceInfoProviderDefault
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager

class BaseApplication :Application(){

    companion object{

        lateinit var sHandler:Handler
        lateinit var sContext: Context
        const val appKey="3a2515bc7ceb481572db3272325e2f4b"
        const val appSecret="7969223921608181a312649b2c3c0a51"
        const val packId="com.example.himalaya"
        const val TestToken="DoubleYellowIce"
        const val per_page_track_count="50"
        const val recommend_count="10"//the maximum of this value is 10

    }
    override fun onCreate() {
        super.onCreate()

        val mXimalaya=CommonRequest.getInstanse()
        mXimalaya.setAppkey(appKey)
        mXimalaya.setPackid(packId)
        mXimalaya.init(this, appSecret,object : DeviceInfoProviderDefault(this) {
            override fun oaid(): String {
            return String()
            }
        })
        sHandler=Handler(applicationContext.mainLooper)
        sContext=baseContext
        XmPlayerManager.getInstance(this).init()
    }
}