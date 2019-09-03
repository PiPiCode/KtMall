package com.outside.baselibrary.injection.component

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.outside.baselibrary.injection.ActivityScope
import com.outside.baselibrary.injection.module.ActivityModule
import com.outside.baselibrary.injection.module.LifeCycleProviderModule
import com.trello.rxlifecycle3.LifecycleProvider
import dagger.Component
import javax.inject.Singleton

/**
 * className:    ActivityComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/3 9:44
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
    modules = arrayOf(ActivityModule::class,LifeCycleProviderModule::class))
interface ActivityComponent {
    //要想子类使用必须显式得暴露出来
    fun activity(): AppCompatActivity
    fun context(): Context
    fun lifecycleProvider():LifecycleProvider<*>
}