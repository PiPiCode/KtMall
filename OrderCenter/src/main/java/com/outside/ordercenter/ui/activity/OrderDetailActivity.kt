package com.outside.ordercenter.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route

import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.baselibrary.utils.YuanFenConverter
import com.outside.ordercenter.R
import com.outside.ordercenter.data.protocol.Order
import com.outside.ordercenter.injection.component.DaggerOrderComponent
import com.outside.ordercenter.injection.module.OrderModule
import com.outside.ordercenter.presenter.OrderDetailPresenter
import com.outside.ordercenter.presenter.view.OrderDetailView
import com.outside.ordercenter.ui.adapter.OrderGoodsAdapter
import com.outside.provider.common.ProviderConstant
import com.outside.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_detail.*

/*
    订单详情
 */
@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
class OrderDetailActivity :BaseMvpActivity<OrderDetailPresenter>(), OrderDetailView {

    private lateinit var mAdapter: OrderGoodsAdapter


    override fun getLayoutId(): Int {
        return R.layout.activity_order_detail
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent).orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun initView() {
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter

    }

    override fun initData() {
        super.initData()
        mPresenter.getOrderById(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID,-1))
    }

    override fun onGetOrderByIdResult(result: Order) {
        mShipNameTv.setContentText(result.shipAddress!!.shipUserName)
        mShipMobileTv.setContentText(result.shipAddress!!.shipUserMobile)
        mShipAddressTv.setContentText(result.shipAddress!!.shipAddress)
        mTotalPriceTv.setContentText(YuanFenConverter.changeF2YWithUnit(result.totalPrice))

        mAdapter.setData(result.orderGoodsList)
    }



}