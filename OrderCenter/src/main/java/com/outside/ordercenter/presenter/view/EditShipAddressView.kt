package com.outside.ordercenter.presenter.view

import com.outside.baselibrary.presenter.view.BaseView

/**
 * className:    EditShipAddressView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/9 15:28
 */

interface EditShipAddressView :BaseView{

    fun onAddshipAdressResult(boolean: Boolean)
    fun onEditshipAdressResult(t: Boolean)

}