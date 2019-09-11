package com.outside.usercenter.presenter

import com.outside.baselibrary.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.usercenter.presenter.view.ResetPwdView
import com.outside.usercenter.service.UserService
import javax.inject.Inject


/**
 * className:    RegisterPresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

class ResetPwdPresenter @Inject constructor(): BasePresenter<ResetPwdView>(){

    @Inject
    lateinit var userService: UserService

    fun resetPwd(phone: String, pwd: String) {

        if (!checkNetWork()){
            return
        }
        mView.showLoading()

        userService.forgetPwd(phone, pwd)
            .excute(object :BaseObserver<Boolean>(mView){
                override fun onNext(t: Boolean) {
                    if (t){
                        mView.onResetPwdResult("重置密码成功")
                    }
                }
            },lifecycleProvider)
    }
}