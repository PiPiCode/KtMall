package com.outside.goodscenter.presenter

import com.outside.baselibrary.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.baselibrary.utils.AppPrefsUtils
import com.outside.goodscenter.common.GoodsConstant
import com.outside.goodscenter.data.protocol.Goods
import com.outside.goodscenter.presenter.view.GoodsDetailView
import com.outside.goodscenter.service.CartService
import com.outside.goodscenter.service.GoodsService
import javax.inject.Inject

class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var cartService: CartService

    /*
        获取商品详情
     */
    fun getGoodsDetail(goodsId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId).excute(object : BaseObserver<Goods>(mView) {
            override fun onNext(t: Goods) {
                mView.onGetGoodsDetailResult(t)
            }
        }, lifecycleProvider)
    }

    /*
        加入购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.addCart(goodsId,goodsDesc,goodsIcon,goodsPrice,
            goodsCount,goodsSku).excute(object : BaseObserver<Int>(mView) {
            override fun onNext(t: Int) {
                AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE,t)
                mView.onAddCartResult(t)
            }
        }, lifecycleProvider)
    }

}
