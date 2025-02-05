package com.outside.ordercenter.injection.component

import com.outside.baselibrary.injection.PerComponentScope
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.ordercenter.injection.module.ShipAddressModule
import com.outside.ordercenter.ui.activity.ShipAddressActivity
import com.outside.ordercenter.ui.activity.ShipAddressEditActivity
import dagger.Component

/**
 * className:    ShipAddressComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/9 15:29
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponent {
    fun inject(activity: ShipAddressActivity)
    fun inject(activity: ShipAddressEditActivity)

}