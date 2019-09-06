package com.kotlin.goods.ui.fragment

import com.kotlin.base.ext.loadUrl
import com.kotlin.goods.event.GoodsDetailImageEvent
import com.outside.baselibrary.rx.RxBus
import com.outside.baselibrary.rx.registerInBus
import com.outside.baselibrary.ui.fragment.BaseFragment
import com.outside.goodscenter.R
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/*
    商品详情Tab Two
 */
class GoodsDetailTabTwoFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_goods_detail_tab_two
    }

    override fun initView() {
        initObserve()
    }


    /*
        初始化监听，商品详情获取成功后，加载当前页面
     */
    private fun initObserve() {
        RxBus.observe<GoodsDetailImageEvent>()
                .subscribe {
                    t: GoodsDetailImageEvent ->
                    run {
                        mGoodsDetailOneIv.loadUrl(t.imgOne)
                        mGoodsDetailTwoIv.loadUrl(t.imgTwo)
                    }
                }
                .registerInBus(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.unRegister(this)
    }
}
