package com.outside.baselibrary.common

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.outside.baselibrary.injection.component.AppComponent
import com.outside.baselibrary.injection.component.DaggerAppComponent
import com.outside.baselibrary.injection.module.AppModule

/**
 * className:    BaseApplication
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/3 9:44
 */

open class BaseApplication : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        context = this
        initAppInjection()
        ARouter.openLog()   // 打印日志
        ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this) // 尽可能早，推荐在Application中初始化

    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var context: Context
    }

}