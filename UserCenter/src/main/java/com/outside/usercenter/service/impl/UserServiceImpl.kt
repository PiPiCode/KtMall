package com.outside.usercenter.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.baselibrary.rx.BaseException
import com.outside.baselibrary.rx.BaseFuncBoolean
import com.outside.usercenter.data.protocol.UserInfo
import com.outside.usercenter.data.repository.UserRepository
import com.outside.usercenter.service.UserService
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

/**
 * className:    UserServiceImpl
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:49
 */

class UserServiceImpl @Inject constructor() : UserService {



    @Inject
    lateinit var userRepository: UserRepository


    //注册
    override fun register(phone: String, verifyCode: String, pwd: String): Observable<Boolean> {
        //注册访问网络请求 返回Observable对象BaseResp<String>，再通过flatmap变换处理成 Observable<Boolean>
        return userRepository.register(phone, verifyCode, pwd)
            .convertBoolean()
    }

    //登录
    override fun login(phone: String, pwd: String, pushId: String): Observable<UserInfo> {
        //注册访问网络请求 返回Observable对象BaseResp<String>，再通过flatmap变换处理成 Observable<Boolean>
        return userRepository.login(phone, pwd, pushId)
            .convert()
    }

    //忘记密码
    override fun forgetPwd(phone: String, verifyCode: String): Observable<Boolean> {
        return userRepository.forgetPwd(phone,verifyCode).convert()
    }

    //重置密码
    override fun resetPwd(phone: String, pwd: String): Observable<Boolean> {
        return userRepository.forgetPwd(phone,pwd).convert()
    }
}