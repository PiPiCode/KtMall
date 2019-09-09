package com.outside.ordercenter.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.ext.onClick
import com.kotlin.base.ext.setVisible
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.event.SelectAddressEvent
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import com.outside.baselibrary.rx.RxBus
import com.outside.baselibrary.rx.registerInBus
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.ordercenter.R
import com.outside.ordercenter.presenter.OrderConfirmPresenter
import com.outside.ordercenter.presenter.view.OrderConfirmView
import com.outside.provider.router.RouterPath
import com.outside.usercenter.injection.component.DaggerOrderComponent
import com.outside.usercenter.injection.module.OrderModule
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
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        RxBus.unRegister(this)
    }

}