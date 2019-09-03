package com.outside.baselibrary.rx

import com.outside.baselibrary.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.adapter.rxjava2.Result.response
import okhttp3.ResponseBody
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.outside.baselibrary.common.BaseConstant


/**
 * className:    BaseObserver
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:59
 */

open class BaseObserver<T>(val basaView:BaseView):Observer<T> {

    override fun onComplete() {
        basaView.hideLoading()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        basaView.hideLoading()
        if(e is BaseException){
            basaView.onError(e.msg)
        }else{
            basaView.onError(BaseConstant.SERVER_ERROR)
        }
    }
}