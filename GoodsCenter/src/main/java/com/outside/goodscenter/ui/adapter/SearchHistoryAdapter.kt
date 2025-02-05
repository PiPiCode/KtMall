package com.outside.goodscenter.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outside.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.outside.goodscenter.R
import kotlinx.android.synthetic.main.layout_search_history_item.view.*

/*
    搜索历史记录
 */
class SearchHistoryAdapter(context: Context) : BaseRecyclerViewAdapter<String, SearchHistoryAdapter.ViewHolder>(context) {

   override fun onCreateViewHolder(parent: ViewGroup,
                           viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(
                    R.layout.layout_search_history_item,
                        parent,
                        false)
       return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mSearchHistoryTv.text = model

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
