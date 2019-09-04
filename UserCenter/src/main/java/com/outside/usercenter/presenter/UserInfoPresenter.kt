package com.outside.usercenter.presenter

import com.kotlin.base.ext.excute
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.usercenter.data.protocol.UserInfo
import com.outside.usercenter.presenter.view.UserInfoView
import com.outside.usercenter.service.UploadService
import com.outside.usercenter.service.UserService
import javax.inject.Inject


/**
 * className:    RegisterPresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken() {
        if (!checkNetWork()) return
        mView.showLoading()
        uploadService.getUploadToken().excute(object : BaseObserver<String>(mView) {
            override fun onNext(t: String) {
                super.onNext(t)
                mView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)
    }

    fun updateUser(userIcon: String,userName: String,userGender:String, userSign: String ){
        if (!checkNetWork()) return
        mView.showLoading()
        userService.updateUser(userIcon,userName,userGender,userSign).excute(
            object :BaseObserver<UserInfo>(mView){
                override fun onNext(t: UserInfo) {
                    super.onNext(t)
                    mView.onUpdateResult(t)
                }
            }
        ,lifecycleProvider)
    }
}