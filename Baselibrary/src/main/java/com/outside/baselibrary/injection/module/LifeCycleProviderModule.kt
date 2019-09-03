package com.outside.baselibrary.injection.module

import com.trello.rxlifecycle3.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * className:    ActivityModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/3 9:44
 */
@Module
class LifeCycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*>{
        return lifecycleProvider
    }
}