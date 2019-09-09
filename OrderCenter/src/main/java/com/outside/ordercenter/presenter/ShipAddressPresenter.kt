package com.outside.ordercenter.presenter

import com.kotlin.base.ext.excute
import com.kotlin.order.data.protocol.DeleteShipAddressReq
import com.kotlin.order.data.protocol.EditShipAddressReq
import com.kotlin.order.data.protocol.ShipAddress
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.ordercenter.presenter.view.ShipAddressView
import com.outside.ordercenter.service.ShipAddressService
import javax.inject.Inject

class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var service: ShipAddressService


    fun getShipAddressList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.getShipAddressList()
            .excute(object : BaseObserver<MutableList<ShipAddress>?>(mView) {
                override fun onNext(res: MutableList<ShipAddress>?) {
                    mView.onGetshipAdressResult(res!!)
                }

            }, lifecycleProvider)
    }


    // 设置默认收货地址
    fun setDefaultAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.editShipAddress(address).excute(object : BaseObserver<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                super.onNext(t)
                mView.onDefaultAdressResult(t)
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

    //删除地址
    fun deleteShipAddress(id: Int){
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.deleteShipAddress(DeleteShipAddressReq(id)).excute(object :BaseObserver<Boolean>(mView){

        },lifecycleProvider)
    }

}
