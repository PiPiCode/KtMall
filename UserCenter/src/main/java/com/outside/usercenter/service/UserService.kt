package com.outside.usercenter.service

import com.outside.usercenter.data.protocol.UserInfo
import io.reactivex.Observable

/**
 * className:    UserService
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:48
 */

interface UserService {

    fun register(phone: String, verifyCode: String, pwd: String): Observable<Boolean>
    fun login(phone: String, pwd: String, pushId: String): Observable<UserInfo>
    fun forgetPwd(phone: String, verifyCode: String): Observable<Boolean>
    fun resetPwd(phone: String, pwd: String): Observable<Boolean>
}