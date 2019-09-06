package com.outside.goodscenter.presenter

import com.kotlin.base.ext.excute
import com.kotlin.goods.data.protocol.Goods
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.goodscenter.presenter.view.GoodsView
import com.outside.goodscenter.service.GoodsService
import javax.inject.Inject

class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsView>() {

    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int){

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        goodsService.getGoodsList(categoryId,pageNo)
            .excute(object : BaseObserver<MutableList<Goods>?>(mView) {
                override fun onNext(t: MutableList<Goods>?) {
                    super.onNext(t)
                    if (t.isNullOrEmpty()) {
                        mView.onGetGoodsResult(t)
                    } else {
                        mView.onError("数据解析错误")
                    }
                }
            }, lifecycleProvider)
    }
}
