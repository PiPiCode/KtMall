package com.outside.ordercenter.presenter

import com.outside.baselibrary.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.ordercenter.data.protocol.ShipAddress
import com.outside.ordercenter.presenter.view.EditShipAddressView
import com.outside.ordercenter.service.ShipAddressService
import javax.inject.Inject

class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var service: ShipAddressService

    //添加收货地址
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.addShipAddress(shipUserName, shipUserMobile, shipAddress)
            .excute(object : BaseObserver<Boolean>(mView) {
                override fun onNext(b: Boolean) {
                    mView.onAddshipAdressResult(b)
                }
            }, lifecycleProvider)
    }


    // 修改收货地址
    fun editShipAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.editShipAddress(address).excute(object : BaseObserver<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                super.onNext(t)
                mView.onEditshipAdressResult(t)
            }
        }, lifecycleProvider)
    }
}
