package com.outside.goodscenter.presenter

import com.kotlin.base.ext.excute
import com.kotlin.goods.data.protocol.Category
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.goodscenter.presenter.view.CategoryView
import com.outside.goodscenter.service.CategoryService

import javax.inject.Inject


/**
 * className:    CategoryPresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory(parentId: String) {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        categoryService.getCategory(parentId)
            .excute(object : BaseObserver<MutableList<Category>?>(mView) {
                override fun onNext(t: MutableList<Category>?) {
                    super.onNext(t)
                    if (t.isNullOrEmpty()){
                        mView.onGetCategoryResult(t)
                    }else{
                        mView.onError("数据解析错误")
                    }
                }
            }, lifecycleProvider)
    }
}