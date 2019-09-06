package com.outside.goodscenter.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.kotlin.base.ext.startLoading
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.ui.adapter.CartGoodsAdapter
import com.outside.baselibrary.ui.fragment.BaseMvpFragment
import com.outside.goodscenter.R
import com.outside.goodscenter.presenter.CartPresenter
import com.outside.goodscenter.presenter.view.CartView
import com.outside.usercenter.injection.component.DaggerCartComponent
import com.outside.usercenter.injection.module.CartModule
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * className:    CartFragment
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/5 20:14
 */

class CartFragment : BaseMvpFragment<CartPresenter>(), CartView {


    lateinit var cartGoodsAdapter: CartGoodsAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_cart
    }

    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(activityComponent).cartModule(CartModule())
            .build().inject(this)
        mPresenter.mView = this

    }

    override fun initView() {

        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        cartGoodsAdapter = CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter = cartGoodsAdapter
        //单项点击事件
        cartGoodsAdapter.onItemClick { item, position ->

        }

        loadData()
    }

    private fun loadData() {
        mMultiStateView.startLoading()

        mPresenter.getCartList()
    }

    override fun getCartList(result: MutableList<CartGoods>?) {
        if(result!=null && result.size>0){
            cartGoodsAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

}