package com.outside.goodscenter.ui.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outside.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.outside.goodscenter.R
import com.outside.goodscenter.data.protocol.Category
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

/*
    一级商品分类 Adapter
 */
class TopCategoryAdapter(context: Context): BaseRecyclerViewAdapter<Category, TopCategoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_top_category_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //分类名称
        holder.itemView.mTopCategoryNameTv.text = model.categoryName
        //是否被选中
        holder.itemView.mTopCategoryNameTv.isSelected = model.isSelected

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

}
