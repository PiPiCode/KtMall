package com.outside.goodscenter.ui.fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.outside.baselibrary.ext.startLoading
import com.outside.baselibrary.ui.fragment.BaseMvpFragment
import com.outside.goodscenter.R
import com.outside.goodscenter.common.GoodsConstant
import com.outside.goodscenter.data.protocol.Category
import com.outside.goodscenter.injection.component.DaggerCategoryComponent
import com.outside.goodscenter.injection.module.CategoryModule
import com.outside.goodscenter.presenter.CategoryPresenter
import com.outside.goodscenter.presenter.view.CategoryView
import com.outside.goodscenter.ui.activity.GoodsActivity
import com.outside.goodscenter.ui.adapter.SecondCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * className:    CategoryFragment
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/5 20:14
 */

class CategoryFragment:BaseMvpFragment<CategoryPresenter>() ,CategoryView{

    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_category
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent).categoryModule(
            CategoryModule()
        )
            .build().inject(this)
        mPresenter.mView = this

    }

    override fun initView() {

        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context!!)
        mTopCategoryRv.adapter = topAdapter
        //单项点击事件
        topAdapter.onItemClick { item, position ->
            for (category in topAdapter.dataList) {
                category.isSelected = item.id == category.id
            }
            topAdapter.notifyDataSetChanged()

            loadData(item.id)
        }


        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.onItemClick { item, _ ->
            startActivity<GoodsActivity>(GoodsConstant.KEY_CATEGORY_ID  to item.id)
        }

        loadData()
    }

    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }

        mPresenter.getCategory(parentId.toString())
    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        result?.let {
            if(it[0].parentId == "0"){
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id.toString())
            }else{
                secondAdapter.setData(result)
            }
        }
    }

}