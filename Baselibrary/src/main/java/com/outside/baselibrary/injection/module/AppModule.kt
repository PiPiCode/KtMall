package com.outside.baselibrary.injection.module

import android.content.Context
import com.outside.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * className:    AppModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/3 9:44
 */
@Module
class AppModule(private val context:BaseApplication) {
    @Provides
    @Singleton
    fun providesContext():Context{
        return context
    }
}