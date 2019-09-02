package com.outside.usercenter.data.repository

import com.outside.baselibrary.data.net.RetrofitFactory
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.usercenter.data.api.UserAPi
import com.outside.usercenter.data.protocol.RegisterReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * className:    UserRepository
 * description:  真正发送请求仓库类
 * author:       CLW2018
 * creatTime:    2019/9/2 17:49
 */

class UserRepository @Inject constructor(){

    //注册请求
    fun register(phone: String, verifyCode: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserAPi::class.java)
            .register(RegisterReq(phone, pwd, verifyCode))
    }
}