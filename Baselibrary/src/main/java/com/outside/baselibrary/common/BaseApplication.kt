package com.outside.baselibrary.common

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.outside.baselibrary.injection.component.AppComponent
import com.outside.baselibrary.injection.component.DaggerAppComponent
import com.outside.baselibrary.injection.module.AppModule

/**
 * className:    BaseApplication
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/3 9:44
 */
 
class BaseApplication :MultiDexApplication(){

    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        context = this
        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent =  DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object{
        lateinit var  context: Context
    }

}