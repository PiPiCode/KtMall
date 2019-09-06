package com.outside.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.utils.AppPrefsUtils
import com.outside.baselibrary.common.BaseConstant
import com.outside.provider.router.RouterPath

/**
 * className:    CommonUtils
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/5 19:26
 */
 

fun isLogined():Boolean{
    val isHave = AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN)
    isHave?.let {
        return it.isNotEmpty()
    }
    return false
}

fun afterLogin(method : ()->Unit){
    if (isLogined()){
        method()
    }else{
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}