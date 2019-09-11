package com.outside.goodscenter.presenter.view

import com.outside.baselibrary.presenter.view.BaseView
import com.outside.goodscenter.data.protocol.Goods

/**
 * className:    RegisterView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

interface GoodsDetailView : BaseView {

    fun onGetGoodsDetailResult(result: Goods)

    fun onAddCartResult(result: Int)
}