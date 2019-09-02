package com.outside.usercenter.service

import io.reactivex.Observable

/**
 * className:    UserService
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:48
 */

interface UserService {

    fun register(phone: String, verifyCode: String, pwd: String): Observable<Boolean>
}