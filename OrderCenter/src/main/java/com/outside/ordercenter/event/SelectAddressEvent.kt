package com.outside.ordercenter.event

import com.outside.ordercenter.data.protocol.ShipAddress


/*
    选择收货人信息事件
 */
class SelectAddressEvent(val address: ShipAddress)
