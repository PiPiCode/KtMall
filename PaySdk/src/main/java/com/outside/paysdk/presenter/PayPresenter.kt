package com.outside.paysdk.presenter

import com.outside.baselibrary.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.paysdk.presenter.view.PayView
import com.outside.paysdk.service.PayService
import javax.inject.Inject

/**
 * className:    PayPresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 11:13
 */

class PayPresenter @Inject constructor():BasePresenter<PayView>(){
    @Inject
    lateinit var payService: PayService


    fun getPaySign(orderId: Int, totalPrice: Long){
        if(!checkNetWork()){
            return
        }

        mView.showLoading()
        payService.getPaySign(orderId,totalPrice).excute(object :BaseObserver<String>(mView){
            override fun onNext(t: String) {
                super.onNext(t)
                mView.getPaySignResult(t)
            }

        },lifecycleProvider)
    }


    fun payOrder(orderId: Int){
        if(!checkNetWork()){
            return
        }

        mView.showLoading()
        payService.payOrder(orderId).excute(object :BaseObserver<Boolean>(mView){
            override fun onNext(t: Boolean) {
                super.onNext(t)
                mView.payOrderResult(t)
            }

        },lifecycleProvider)
    }

}