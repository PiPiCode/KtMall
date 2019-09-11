package com.outside.goodscenter.service

import com.outside.goodscenter.data.protocol.Goods
import io.reactivex.Observable

/**
 * className:    CategoryService
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:48
 */

interface GoodsService {

    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>
    fun getGoodsDetail(goodsId: Int):Observable<Goods>
}