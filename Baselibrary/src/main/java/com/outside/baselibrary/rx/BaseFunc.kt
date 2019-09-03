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

class BaseFunc<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(baseResp: BaseResp<T>): Observable<T> {
        if (baseResp.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(baseResp.status, baseResp.message))
        }
        return Observable.just(baseResp.data)
    }
}