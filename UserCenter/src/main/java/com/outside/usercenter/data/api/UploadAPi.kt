package com.outside.usercenter.data.api

import com.outside.baselibrary.data.protocol.BaseResp
import io.reactivex.Observable
import retrofit2.http.POST

/**
 * className:    UploadAPi
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 17:45
 */

interface UploadAPi {

    @POST("common/getUploadToken")
    fun getUploadToken():Observable<BaseResp<String>>
}