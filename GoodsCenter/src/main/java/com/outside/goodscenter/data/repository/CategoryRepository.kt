package com.outside.goodscenter.data.repository

import com.kotlin.goods.data.api.CategoryApi
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.protocol.GetCategoryReq
import com.outside.baselibrary.data.net.RetrofitFactory
import com.outside.baselibrary.data.protocol.BaseResp
import io.reactivex.Observable
import javax.inject.Inject

/**
 * className:    CategoryRepository
 * description:  真正发送请求仓库类
 * author:       CLW2018
 * creatTime:    2019/9/2 17:49
 */

class CategoryRepository @Inject constructor() {

    fun getCategory(parentId: String): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }
}