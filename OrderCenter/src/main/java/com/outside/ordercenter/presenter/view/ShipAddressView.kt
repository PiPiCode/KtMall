package com.outside.ordercenter.presenter.view

import com.outside.baselibrary.presenter.view.BaseView
import com.outside.ordercenter.data.protocol.ShipAddress

/**
 * className:    ShipAddressView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/9 15:28
 */

interface ShipAddressView :BaseView{
    fun onGetshipAdressResult(result: MutableList<ShipAddress>)
    fun onEditshipAdressResult(boolean: Boolean)
    fun onDefaultAdressResult(t: Boolean)

}