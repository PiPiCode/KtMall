package com.outside.goodscenter.injection.component

import com.outside.baselibrary.injection.PerComponentScope
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.goodscenter.injection.module.CategoryModule
import com.outside.goodscenter.ui.fragment.CategoryFragment

import dagger.Component

/**
 * className:    CategoryComponent
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:29
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(categoryFragment:CategoryFragment)

}