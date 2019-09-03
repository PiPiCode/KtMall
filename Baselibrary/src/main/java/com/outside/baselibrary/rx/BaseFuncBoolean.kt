package com.outside.baselibrary.rx

import com.outside.baselibrary.common.ResultCode
import com.outside.baselibrary.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * className:    BaseFuncBoolean
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/3 13:05
 */

class BaseFuncBoolean<T> : Function<BaseResp<T>, Observable<Boolean>> {
    override fun apply(baseResp: BaseResp<T>): Observable<Boolean> {
        if (baseResp.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(baseResp.status, baseResp.message))
        }
        return Observable.just(true)
    }
}