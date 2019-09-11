package com.outside.usercenter.data.repository

import com.outside.baselibrary.data.net.RetrofitFactory
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.usercenter.data.api.UserAPi
import com.outside.usercenter.data.protocol.*
import io.reactivex.Observable
import javax.inject.Inject

/**
 * className:    UserRepository
 * description:  真正发送请求仓库类
 * author:       CLW2018
 * creatTime:    2019/9/2 17:49
 */

class UserRepository @Inject constructor() {

    //注册访问网络请求 返回Observable对象
    fun register(phone: String, verifyCode: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserAPi::class.java)
            .register(RegisterReq(phone, pwd, verifyCode))
    }


    fun login(phone: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserAPi::class.java)
            .login(LoginReq(phone, pwd, pushId))
    }

    fun forgetPwd(phone: String, code: String): Observable<BaseResp<Boolean>> {
        return RetrofitFactory.instance.create(UserAPi::class.java)
            .forgetPwd(ForgetPwdReq(phone, code))
    }


    fun resetPwd(phone: String, pwd: String): Observable<BaseResp<Boolean>> {
        return RetrofitFactory.instance.create(UserAPi::class.java)
            .resetPwd(ResetPwdReq(phone, pwd))
    }

    fun updateUser(userIcon: String,userName: String,userGender:String, userSign: String ): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserAPi::class.java)
            .updateUser(EditUserReq(userIcon,userName,userGender,userSign))
    }
}