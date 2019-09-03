package com.outside.baselibrary.injection.component

import android.content.Context
import com.outside.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * className:    AppComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/3 9:44
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    //暴露给依赖得activity
    fun context(): Context
}