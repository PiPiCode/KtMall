package com.outside.ordercenter.data.protocol

/*
    添加收货地址
 */
data class AddShipAddressReq(val shipUserName: String, val shipUserMobile: String, val shipAddress: String)
