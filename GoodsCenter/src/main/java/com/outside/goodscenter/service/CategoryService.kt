package com.outside.goodscenter.service

import com.outside.goodscenter.data.protocol.Category
import io.reactivex.Observable

/**
 * className:    CategoryService
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:48
 */

interface CategoryService {

    fun getCategory(parentId:String): Observable<MutableList<Category>?>


}