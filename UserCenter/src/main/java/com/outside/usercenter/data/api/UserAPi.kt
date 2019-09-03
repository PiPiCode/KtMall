package com.outside.usercenter.data.api

import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.usercenter.data.protocol.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * className:    UserAPi
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 17:45
 */

interface UserAPi {

    @POST("userCenter/register")
    fun register(@Body req: RegisterReq):Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body req: LoginReq):Observable<BaseResp<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req:ForgetPwdReq):Observable<BaseResp<Boolean>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: ResetPwdReq):Observable<BaseResp<Boolean>>
}