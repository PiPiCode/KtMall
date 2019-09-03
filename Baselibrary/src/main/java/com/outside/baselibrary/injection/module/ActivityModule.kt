package com.outside.baselibrary.injection.module

import androidx.appcompat.app.AppCompatActivity
import com.outside.baselibrary.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * className:    ActivityModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/3 9:44
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun providesActivity(): AppCompatActivity{
        return activity
    }
}