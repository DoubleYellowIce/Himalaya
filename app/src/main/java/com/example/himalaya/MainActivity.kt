package com.example.himalaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.himalaya.base.BaseApplication
import com.example.himalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.banner.CategoryBannerList
import com.ximalaya.ting.android.opensdk.model.category.CategoryList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogUtil.v(BaseApplication.TestToken,"onSuccess")

        val map=HashMap<String,String>()
        CommonRequest.getCategories(map,object:IDataCallBack<CategoryList>{
            override fun onSuccess(p0: CategoryList?) {
                LogUtil.v(BaseApplication.TestToken,"onSuccess")
                val categories=p0?.categories
                categories?.forEach {
                    LogUtil.v(BaseApplication.TestToken, "the name is ${it.categoryName}")
                    LogUtil.v(BaseApplication.TestToken, "the coverUrlLarge is ${it.coverUrlLarge}")
                }
            }

            override fun onError(p0: Int, p1: String?) {

            }

        })
    }

}