package com.outside.goodscenter.presenter.view

import com.kotlin.goods.data.protocol.Category
import com.outside.baselibrary.presenter.view.BaseView

/**
 * className:    RegisterView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

interface CategoryView : BaseView {

    fun onGetCategoryResult(result: MutableList<Category>?)
}