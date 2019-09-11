package com.outside.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * className:    PushProvider
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 16:40
 */

interface PushProvider:IProvider {

    fun getPushId():String
}