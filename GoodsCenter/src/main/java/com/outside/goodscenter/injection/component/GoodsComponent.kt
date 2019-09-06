package com.outside.usercenter.injection.component

import com.kotlin.goods.ui.fragment.GoodsDetailTabOneFragment
import com.outside.baselibrary.injection.PerComponentScope
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.goodscenter.ui.activity.GoodsActivity
import com.outside.usercenter.injection.module.CartModule
import com.outside.usercenter.injection.module.GoodsModule
import dagger.Component

/**
 * className:    GoodsComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:29
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(GoodsModule::class,CartModule::class))
interface GoodsComponent {
    fun inject(goodsActivity:GoodsActivity)
    fun inject(fragment:GoodsDetailTabOneFragment)

}