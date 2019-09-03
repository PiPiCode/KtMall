package com.outside.usercenter.presenter

import com.kotlin.base.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.usercenter.data.protocol.UserInfo
import com.outside.usercenter.presenter.view.LoginView
import com.outside.usercenter.service.UserService
import javax.inject.Inject


/**
 * className:    RegisterPresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService

    fun login(phone: String, pwd: String, pushId: String) {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        userService.login(phone, pwd, pushId)
            .excute(object : BaseObserver<UserInfo>(mView) {
                override fun onNext(userInfo: UserInfo) {
                    mView.onLoginResult(userInfo)
                }
            }, lifecycleProvider)
    }
}