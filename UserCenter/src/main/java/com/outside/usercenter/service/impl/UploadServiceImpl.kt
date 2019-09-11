package com.outside.usercenter.service.impl


import com.outside.baselibrary.ext.convert
import com.outside.usercenter.data.repository.UploadRepository
import com.outside.usercenter.service.UploadService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * className:    UploadServiceImpl
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:49
 */

class UploadServiceImpl @Inject constructor() : UploadService {

    lateinit var uploadRepository: UploadRepository

    override fun getUploadToken(): Observable<String> {
        return uploadRepository.getUploadToken().convert()
    }

}