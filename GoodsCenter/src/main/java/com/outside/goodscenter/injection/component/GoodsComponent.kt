package com.outside.goodscenter.injection.component

import com.outside.baselibrary.injection.PerComponentScope
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.goodscenter.injection.module.CartModule
import com.outside.goodscenter.injection.module.GoodsModule
import com.outside.goodscenter.ui.activity.GoodsActivity
import com.outside.goodscenter.ui.fragment.GoodsDetailTabOneFragment

import dagger.Component

/**
 * className:    GoodsComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:29
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(GoodsModule::class, CartModule::class))
interface GoodsComponent {
    fun inject(goodsActivity:GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)

}