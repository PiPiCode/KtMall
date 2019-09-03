package com.outside.usercenter.presenter

import com.outside.baselibrary.presenter.BasePresenter
import com.outside.usercenter.presenter.view.UserInfoView
import com.outside.usercenter.service.UserService
import javax.inject.Inject


/**
 * className:    RegisterPresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

class UserInfoPresenter @Inject constructor(): BasePresenter<UserInfoView>(){

    @Inject
    lateinit var userService: UserService


}