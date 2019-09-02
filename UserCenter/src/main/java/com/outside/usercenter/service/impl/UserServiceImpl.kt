package com.outside.usercenter.service.impl

import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.baselibrary.rx.BaseException
import com.outside.usercenter.data.repository.UserRepository
import com.outside.usercenter.service.UserService
import io.reactivex.Observable
import io.reactivex.functions.Action
import io.reactivex.functions.Function
import javax.inject.Inject

/**
 * className:    UserServiceImpl
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:49
 */

class UserServiceImpl : UserService {

    @Inject
    lateinit var userRepository: UserRepository

    override fun register(phone: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return userRepository.register(phone, verifyCode, pwd)
            .flatMap(object : Function<BaseResp<String>, Observable<Boolean>>{
                override fun apply(t: BaseResp<String>): Observable<Boolean> {
                    if (t.status != 0) {
                        return  Observable.error(BaseException(t.status,t.message))

                    }
                    return Observable.just(true)
                }

            })
    }
}