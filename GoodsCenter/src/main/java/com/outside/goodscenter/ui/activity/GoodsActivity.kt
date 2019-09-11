package com.outside.goodscenter.ui.activity

import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.goodscenter.presenter.GoodsListPresenter
import com.outside.goodscenter.presenter.view.GoodsView

import androidx.recyclerview.widget.GridLayoutManager
import com.kennyc.view.MultiStateView
import com.outside.baselibrary.ext.startLoading
import com.outside.goodscenter.R
import com.outside.goodscenter.common.GoodsConstant
import com.outside.goodscenter.data.protocol.Goods
import com.outside.goodscenter.injection.component.DaggerGoodsComponent
import com.outside.goodscenter.injection.module.GoodsModule
import com.outside.goodscenter.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.startActivity


/**
 * className:    GoodsActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/6 10:20
 */

class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsView {

    lateinit var mGoodsAdapter: GoodsAdapter
    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1

    override fun getLayoutId(): Int {
        return R.layout.activity_goods
    }

    override fun injectComponent() {

        DaggerGoodsComponent.builder().activityComponent(activityComponent)
            .goodsModule(GoodsModule())
            .build()
            .inject(this)

    }

    override fun initView() {

        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter

        mGoodsAdapter.onItemClick { t, _ ->
            startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to t.id)
        }

        mRefreshLayout.setOnRefreshListener {
            //refreshlayout.finishRefresh(2000/*,false*/)//传入false表示刷新失败
            mCurrentPage = 1
            initData()
        }

        mRefreshLayout.setOnLoadMoreListener {
            // refreshlayout.finishLoadMore(2000/*,false*/)//传入false表示加载失败
            if (mCurrentPage < mMaxPage) {
                mCurrentPage++
                initData()
            } else {
                mRefreshLayout.finishLoadMore()
            }
        }

    }

    override fun initData() {
        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, mCurrentPage), 1)
    }

    override fun onGetGoodsResult(result: MutableList<Goods>?) {
        mRefreshLayout.finishLoadMore()
        mRefreshLayout.finishRefresh()
        if (result != null && result.size > 0) {
            mMaxPage = result[0].maxPage

            if (mCurrentPage == 1) {
                //刷新
                mGoodsAdapter.setData(result)
            } else {
                //加载更多
                mGoodsAdapter.addData(result)
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}