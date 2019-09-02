package com.outside.usercenter.presenter

import com.kotlin.base.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.usercenter.service.impl.UserServiceImpl
import com.outside.usercenter.presenter.view.RegisterView
import com.outside.usercenter.service.UserService
import javax.inject.Inject


/**
 * className:    RegisterPresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>(){

    @Inject
    lateinit var userService: UserService
    fun register(phone: String, verifyCode: String, pwd: String) {


        userService.register(phone, verifyCode, pwd)
            .excute(object :BaseObserver<Boolean>(){
                override fun onNext(t: Boolean) {
                    mView.onRegisterResult(t)
                }
            })

    }
}