package com.outside.ordercenter.ui.activity

import com.kotlin.base.ext.onClick
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddress
import com.outside.baselibrary.ui.activity.BaseActivity
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.ordercenter.R
import com.outside.ordercenter.presenter.EditShipAddressPresenter
import com.outside.ordercenter.presenter.view.EditShipAddressView
import com.outside.usercenter.injection.component.DaggerShipAddressComponent
import com.outside.usercenter.injection.module.ShipAddressModule
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * className:    ShipAddressEditActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/9 15:16
 */

class ShipAddressEditActivity:BaseMvpActivity<EditShipAddressPresenter>(),EditShipAddressView {


    private var mAddress:ShipAddress? =  null
    override fun getLayoutId(): Int {
        return R.layout.activity_edit_address

    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent).shipAddressModule(
            ShipAddressModule()).build().inject(this)
        mPresenter.mView = this

    }

    override fun initView() {
        mSaveBtn.onClick {
            if (mShipNameEt.text.isNullOrEmpty()){
                toast("名称不能为空")
                return@onClick
            }
            if (mShipMobileEt.text.isNullOrEmpty()){
                toast("电话不能为空")
                return@onClick
            }

            if (mShipAddressEt.text.isNullOrEmpty()){
                toast("地址不能为空")
                return@onClick
            }
            if (mAddress == null){
                mPresenter.addShipAddress(mShipNameEt.text.toString(),
                    mShipMobileEt.text.toString(),
                    mShipAddressEt.text.toString())
            }else{
                mAddress!!.shipUserName = mShipNameEt.text.toString()
                mAddress!!.shipUserMobile = mShipMobileEt.text.toString()
                mAddress!!.shipAddress = mShipAddressEt.text.toString()
                mPresenter.editShipAddress(mAddress!!)
            }

        }


    }

    override fun initData() {
        super.initData()
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)

        }


    }
    override fun onAddshipAdressResult(boolean: Boolean) {
        toast("添加地址成功")
        finish()
    }
    override fun onEditshipAdressResult(t: Boolean) {
        toast("修改地址成功")
        finish()
    }

}