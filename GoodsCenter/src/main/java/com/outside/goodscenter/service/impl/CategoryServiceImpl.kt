package com.outside.goodscenter.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.goods.data.protocol.Category
import com.outside.goodscenter.data.repository.CategoryRepository
import com.outside.goodscenter.service.CategoryService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * className:    CategoryServiceImpl
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:49
 */

class CategoryServiceImpl @Inject constructor() : CategoryService {



    @Inject
    lateinit var categoryRepository: CategoryRepository


    override fun getCategory(parentId: String): Observable<MutableList<Category>?> {

        return categoryRepository.getCategory(parentId).convert()
    }
}