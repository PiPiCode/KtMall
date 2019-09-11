package com.outside.ordercenter.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.outside.baselibrary.ext.onClick
import com.outside.baselibrary.ext.setVisible
import com.outside.baselibrary.rx.RxBus
import com.outside.baselibrary.rx.registerInBus
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.baselibrary.utils.YuanFenConverter
import com.outside.ordercenter.R
import com.outside.ordercenter.data.protocol.Order
import com.outside.ordercenter.event.SelectAddressEvent
import com.outside.ordercenter.injection.component.DaggerOrderComponent
import com.outside.ordercenter.injection.module.OrderModule
import com.outside.ordercenter.presenter.OrderConfirmPresenter
import com.outside.ordercenter.presenter.view.OrderConfirmView
import com.outside.ordercenter.ui.adapter.OrderGoodsAdapter
import com.outside.provider.common.ProviderConstant
import com.outside.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * className:    OrderConfirmActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/9 14:32
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField //kotlin需要加此句
    var mOrderId: Int = 0

    private lateinit var mAdapter: OrderGoodsAdapter
    private var mCurrentOrder: Order? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_order_confirm
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
            .orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun initView() {
        ARouter.getInstance().inject(this)
        mAdapter = OrderGoodsAdapter((this))
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)

        initObserve()
        loadData()

        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }
        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }
        mSubmitOrderBtn.onClick {
            mCurrentOrder?.let {
                mPresenter.submitOrder(it)
            }
        }


    }

    private fun initObserve() {
        RxBus.observe<SelectAddressEvent>().subscribe { o ->
            mCurrentOrder?.let {
                it.shipAddress = o.address
                updateAddressView()
            }
        }.registerInBus(this)
    }


    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    override fun getOrderResult(result: Order) {
        mCurrentOrder = result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
        updateAddressView()
    }


    private fun updateAddressView() {
        mCurrentOrder?.let {
            if (it.shipAddress == null) {
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            } else {
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)

                mShipNameTv.text = it.shipAddress!!.shipUserName + "    " +
                        it.shipAddress!!.shipUserMobile
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }

    }

    override fun submitOrderResult(t: Boolean) {
        toast("订单提交成功")
        ARouter.getInstance().build(RouterPath.PaySDK.PATH_SDK)
            .withInt(ProviderConstant.KEY_ORDER_ID,mCurrentOrder!!.id)
            .withLong(ProviderConstant.KEY_ORDER_PRICE,mCurrentOrder!!.totalPrice)
            .navigation()
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        RxBus.unRegister(this)
    }

}