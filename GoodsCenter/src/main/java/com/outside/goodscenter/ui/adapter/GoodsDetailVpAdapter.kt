package com.outside.goodscenter.ui.adapter

import android.content.Context

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.outside.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import com.outside.goodscenter.ui.fragment.GoodsDetailTabTwoFragment

/*
    商品详情ViewPager Adapter
 */
class GoodsDetailVpAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("商品", "详情")

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return GoodsDetailTabOneFragment()
        }

        return GoodsDetailTabTwoFragment()
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
