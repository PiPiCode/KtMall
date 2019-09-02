package com.outside.usercenter.injection.component

import com.outside.usercenter.injection.module.UserModule
import com.outside.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * className:    UserComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:29
 */
@Component(modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity:RegisterActivity)
}