package com.kotlin.order.ui.fragment


import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.startLoading
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.ui.adapter.OrderAdapter
import com.outside.baselibrary.ui.fragment.BaseMvpFragment
import com.outside.ordercenter.R
import com.outside.ordercenter.presenter.OrderListPresenter
import com.outside.ordercenter.presenter.view.OrderListView
import com.outside.usercenter.injection.component.DaggerOrderComponent
import com.outside.usercenter.injection.module.OrderModule
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.toast


/*
    订单列表Fragment
 */
class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView {


    private lateinit var mAdapter: OrderAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_order
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
            .orderModule(OrderModule()).build()
            .inject(this)
        mPresenter.mView = this

    }

    override fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mAdapter = OrderAdapter(activity!!)
        mOrderRv.adapter = mAdapter
        mAdapter.listener = object : OrderAdapter.OnOptClickListener {
            override fun onOptClick(optType: Int, order: Order) {
                when (optType) {
                    OrderConstant.OPT_ORDER_PAY -> {

                    }
                    OrderConstant.OPT_ORDER_CANCEL -> {
                        showCancelDialog(order.id)
                    }
                    OrderConstant.OPT_ORDER_CONFIRM -> {

                        mPresenter.confirmOrder(order.id)
                    }
                }
            }
        }
        loadData()
    }

    private fun showCancelDialog(id: Int) {

        AlertView(
            "取消订单", "确定取消该订单？", "取消", null, arrayOf("确定"), activity,
            AlertView.Style.Alert, OnItemClickListener { _, position ->
                if (position == 0) {
                    mPresenter.cancelOrder(id)
                }
            }).show()
    }



    private fun loadData() {
        mMultiStateView.startLoading()
        arguments?.let {
            val type = it.getInt(OrderConstant.KEY_ORDER_STATUS)
            mPresenter.getOrderList(type)
        }
    }

    override fun getOrderListResult(t: MutableList<Order>?) {
        if (t != null && t.size > 0) {

            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun confirmOrderResult(b: Boolean) {
        toast("确认收货成功")
        loadData()
    }

    override fun cancelOrderResult(b: Boolean) {
        toast("取消订单成功")
        loadData()
    }

}
