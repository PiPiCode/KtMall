package com.outside.usercenter.injection.component

import com.outside.baselibrary.injection.PerComponentScope
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.goodscenter.ui.fragment.CartFragment
import com.outside.usercenter.injection.module.CartModule
import dagger.Component

/**
 * className:    CartComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:29
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(CartModule::class))
interface CartComponent {

    fun inject(cartFragment: CartFragment)

}