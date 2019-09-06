package com.outside.goodscenter.presenter

import com.kotlin.base.ext.excute
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.service.CartService
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.goodscenter.presenter.view.CartView

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
}