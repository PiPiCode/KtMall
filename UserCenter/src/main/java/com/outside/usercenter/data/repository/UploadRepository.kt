package com.outside.usercenter.data.repository

import com.outside.baselibrary.data.net.RetrofitFactory
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.usercenter.data.api.UploadAPi
import io.reactivex.Observable
import javax.inject.Inject

/**
 * className:    UploadRepository
 * description:  真正发送请求仓库类
 * author:       CLW2018
 * creatTime:    2019/9/2 17:49
 */

class UploadRepository @Inject constructor() {

    //注册访问网络请求 返回Observable对象
    fun getUploadToken(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadAPi::class.java)
            .getUploadToken()
    }

}