package com.outside.paysdk.injection.component

import com.outside.baselibrary.injection.PerComponentScope
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.paysdk.injection.module.PayModule
import com.outside.paysdk.ui.activity.CashRegisterActivity
import dagger.Component

/**
 * className:    PayComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 11:18
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(PayModule::class))
interface PayComponent {
    fun inject(activity:CashRegisterActivity)
}