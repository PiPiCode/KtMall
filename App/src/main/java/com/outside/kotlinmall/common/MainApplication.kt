package com.outside.kotlinmall.common

import cn.jpush.android.api.JPushInterface
import com.outside.baselibrary.common.BaseApplication

/**
 * className:    MainApplication
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 16:20
 */

class MainApplication:BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        //极光初始化
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}