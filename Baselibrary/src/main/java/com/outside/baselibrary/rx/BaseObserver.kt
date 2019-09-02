package com.outside.baselibrary.rx

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * className:    BaseObserver
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:59
 */

open class BaseObserver<T>:Observer<T> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }
}