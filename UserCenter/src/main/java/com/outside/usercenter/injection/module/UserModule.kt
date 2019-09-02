package com.outside.usercenter.injection.module

import com.outside.usercenter.service.UserService
import com.outside.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * className:    UserModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:24
 */
@Module
class UserModule {

    @Provides
    fun providesUserService(service:UserServiceImpl):UserService{
        return service
    }

}