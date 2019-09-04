package com.outside.usercenter.presenter.view

import com.outside.baselibrary.presenter.view.BaseView
import com.outside.usercenter.data.protocol.UserInfo

/**
 * className:    RegisterView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(result:String)
    fun onUpdateResult(result:UserInfo)

}