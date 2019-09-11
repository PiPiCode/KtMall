package com.outside.goodscenter.presenter

import com.outside.baselibrary.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.goodscenter.data.protocol.CartGoods
import com.outside.goodscenter.presenter.view.CartView
import com.outside.goodscenter.service.CartService

import javax.inject.Inject


/**
 * className:    CartPresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

class CartPresenter @Inject constructor() : BasePresenter<CartView>() {

    @Inject
    lateinit var cartService: CartService

    fun getCartList() {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        cartService.getCartList()
            .excute(object : BaseObserver<MutableList<CartGoods>?>(mView) {
                override fun onNext(t: MutableList<CartGoods>?) {
                    super.onNext(t)
                    if (t.isNullOrEmpty()){
                        mView.getCartList(t)
                    }else{
                        mView.onError("数据解析错误")
                    }
                }
            }, lifecycleProvider)
    }

    fun deleteCartList(list: List<Int>){
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.deleteCartList(list).excute(object :BaseObserver<Boolean>(mView){
            override fun onNext(t: Boolean) {
                super.onNext(t)
                mView.onDeleteCartList(t)
            }
        },lifecycleProvider)

    }


    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long){
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.submitCart(list,totalPrice).excute(object :BaseObserver<Int>(mView){
            override fun onNext(t: Int) {
                super.onNext(t)
                mView.onSubmitCartListResult(t)
            }
        },lifecycleProvider)

    }
}