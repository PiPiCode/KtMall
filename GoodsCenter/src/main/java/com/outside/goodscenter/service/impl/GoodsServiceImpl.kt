package com.outside.goodscenter.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.data.repository.GoodsRepository
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.goodscenter.service.GoodsService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * className:    CategoryServiceImpl
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:49
 */

class GoodsServiceImpl @Inject constructor() : GoodsService {



    @Inject
    lateinit var goodsRepository: GoodsRepository


    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {

        return goodsRepository.getGoodsList(categoryId,pageNo).convert()
    }

    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return goodsRepository.getGoodsDetail(goodsId).convert()
    }




}