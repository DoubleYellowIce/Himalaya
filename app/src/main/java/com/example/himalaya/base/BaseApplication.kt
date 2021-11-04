package com.example.himalaya.base

import android.app.Application
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.DeviceInfoProviderDefault

class BaseApplication :Application(){

    companion object{
        const val appKey="3a2515bc7ceb481572db3272325e2f4b"
        const val appSecret="7969223921608181a312649b2c3c0a51"
        const val packId="com.example.himalaya"
        const val TestToken="DoubleYellowIce"

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

    }
}