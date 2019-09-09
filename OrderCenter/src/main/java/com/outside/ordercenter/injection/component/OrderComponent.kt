package com.outside.usercenter.injection.component

import com.kotlin.order.ui.fragment.OrderFragment
import com.outside.baselibrary.injection.PerComponentScope
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.ordercenter.ui.activity.OrderConfirmActivity
import com.outside.usercenter.injection.module.OrderModule
import dagger.Component

/**
 * className:    OrderComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:29
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)

    fun inject(fragment:OrderFragment)

}