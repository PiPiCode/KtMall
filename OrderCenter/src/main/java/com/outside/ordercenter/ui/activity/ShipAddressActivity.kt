package com.outside.ordercenter.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.startLoading
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.event.SelectAddressEvent
import com.kotlin.order.ui.adapter.ShipAddressAdapter
import com.outside.baselibrary.rx.RxBus
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.ordercenter.R
import com.outside.ordercenter.presenter.ShipAddressPresenter
import com.outside.ordercenter.presenter.view.ShipAddressView
import com.outside.usercenter.injection.component.DaggerShipAddressComponent
import com.outside.usercenter.injection.module.ShipAddressModule
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * className:    ShipAddressActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/9 15:16
 */

class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView,
    ShipAddressAdapter.OnOptClickListener {


    private lateinit var mAdapter: ShipAddressAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_address
    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent).shipAddressModule(
            ShipAddressModule()
        ).build().inject(this)
        mPresenter.mView = this
    }

    override fun initView() {

        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAdapter = ShipAddressAdapter(this)
        mAddressRv.adapter = mAdapter
        //点击事件监听
        mAdapter.mOptClickListener = this

        mAdapter.onItemClick { t, _ ->
            RxBus.send(SelectAddressEvent(t))
            finish()
        }
    }


    override fun onSetDefault(address: ShipAddress) {
        mPresenter.setDefaultAddress(address)
    }

    override fun onEdit(address: ShipAddress) {
        startActivity<ShipAddressEditActivity>(OrderConstant.KEY_SHIP_ADDRESS to address)
    }

    override fun onDelete(address: ShipAddress) {

        AlertView("删除", "确定要删除该地址？", "取消", null, arrayOf("确定"), this@ShipAddressActivity,
            AlertView.Style.Alert, OnItemClickListener { o, position ->
                if (position == 0) {
                    mPresenter.deleteShipAddress(address.id)
                }
            }).show()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()

    }

    override fun onGetshipAdressResult(result: MutableList<ShipAddress>) {

        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onDefaultAdressResult(t: Boolean) {
        toast("设置默认地址成功")
    }

    override fun onEditshipAdressResult(boolean: Boolean) {
        toast("删除地址成功")
        loadData()
    }

}