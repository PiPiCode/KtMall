package com.outside.goodscenter.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.kennyc.view.MultiStateView
import com.outside.baselibrary.ext.onClick
import com.outside.baselibrary.ext.setVisible
import com.outside.baselibrary.ext.startLoading
import com.outside.baselibrary.rx.RxBus
import com.outside.baselibrary.rx.registerInBus
import com.outside.baselibrary.ui.fragment.BaseMvpFragment
import com.outside.baselibrary.utils.AppPrefsUtils
import com.outside.baselibrary.utils.YuanFenConverter
import com.outside.goodscenter.R
import com.outside.goodscenter.common.GoodsConstant
import com.outside.goodscenter.data.protocol.CartGoods
import com.outside.goodscenter.event.CartAllCheckedEvent
import com.outside.goodscenter.event.UpdateCartSizeEvent
import com.outside.goodscenter.event.UpdateTotalPriceEvent
import com.outside.goodscenter.injection.component.DaggerCartComponent
import com.outside.goodscenter.injection.module.CartModule
import com.outside.goodscenter.presenter.CartPresenter
import com.outside.goodscenter.presenter.view.CartView
import com.outside.goodscenter.ui.adapter.CartGoodsAdapter
import com.outside.provider.common.ProviderConstant
import com.outside.provider.router.RouterPath
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/**
 * className:    CartFragment
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/5 20:14
 */

class CartFragment : BaseMvpFragment<CartPresenter>(), CartView {


    lateinit var cartGoodsAdapter: CartGoodsAdapter
    private var mTotalPrice: Long = 0L

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

        mAllCheckedCb.onClick {
            for (item in cartGoodsAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            cartGoodsAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        mDeleteBtn.onClick {
            val cartIdList:MutableList<Int> =  arrayListOf()
            cartGoodsAdapter.dataList.filter { it.isSelected }
                .mapTo(cartIdList){it.id}

            if(cartIdList.size == 0){
                toast("请选择要删除的数据")
            }else{
                mPresenter.deleteCartList(cartIdList)
            }
        }



        mHeaderBar.getRightView().onClick {
            refeshEditStatus()
        }


        mSettleAccountsBtn.onClick {
            val cartGoodsList:MutableList<CartGoods> =  arrayListOf()
            cartGoodsAdapter.dataList.filter { it.isSelected }
                .mapTo(cartGoodsList){it}

            if(cartGoodsList.size == 0){
                toast("请选择要结算的数据")
            }else{
                mPresenter.submitCart(cartGoodsList,mTotalPrice)
            }
        }

        initObserve()

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }


    private fun refeshEditStatus() {
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)
        mHeaderBar.getRightView().text = if(isEditStatus)getString(R.string.common_complete)else getString(R.string.common_edit)

    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    override fun getCartList(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            cartGoodsAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE,result?.size?:0)
        RxBus.send(UpdateCartSizeEvent())
        updateTotalPrice()
    }

    private fun initObserve() {
        RxBus.observe<CartAllCheckedEvent>().subscribe { t: CartAllCheckedEvent ->
            run {
                mAllCheckedCb.isChecked = t.isAllChecked
                updateTotalPrice()
            }
        }
            .registerInBus(this)

        RxBus.observe<UpdateTotalPriceEvent>().subscribe {
                updateTotalPrice()
        }.registerInBus(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.unRegister(this)
    }

    private fun updateTotalPrice() {

        mTotalPrice = cartGoodsAdapter.dataList
            .filter { it.isSelected }
            .map { it.goodsCount * it.goodsPrice }
            .sum()

        mTotalPriceTv.text = "合计：" +
                "${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }
    override fun onDeleteCartList(b: Boolean) {
        toast("删除成功")
        refeshEditStatus()
        loadData()
    }


    override fun onSubmitCartListResult(result: Int) {
        toast(result.toString())
        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
            .withInt(ProviderConstant.KEY_ORDER_ID,result)
            .navigation()
    }

    fun setBackVisible(isVisible:Boolean){
        mHeaderBar.getLeftView().setVisible(isVisible)
    }

}