package com.outside.usercenter.service

import io.reactivex.Observable

/**
 * className:    UploadService
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:48
 */

interface UploadService {

    fun getUploadToken(): Observable<String>

}