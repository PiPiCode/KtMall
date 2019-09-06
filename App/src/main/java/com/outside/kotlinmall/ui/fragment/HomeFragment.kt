package com.outside.kotlinmall.ui.fragment


import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.outside.baselibrary.ui.fragment.BaseFragment
import com.outside.kotlinmall.R
import com.outside.kotlinmall.ui.adapter.HomeDiscountAdapter
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow

/**
 * className:    HomeFragment
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/5 16:46
 */

class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        initBanner()
        initViews()
        initDiscount()
        initTopic()
    }

    private fun initTopic() {
        // 话题
        mTopicPager.adapter = TopicAdapter(context!!, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO,
            HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5
        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30f).spaceSize(0f).build()
    }

    private fun initViews() {
        mNewsFlipperView.setData(
            arrayOf(
                "夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元" +
                        "优惠券"
            )
        )


    }

    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(
            listOf(
                HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_DISCOUNT_FIVE,
                HOME_BANNER_FOUR
            )
        )
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER)
        mHomeBanner.start()

    }

    private fun initDiscount() {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager
        val discountAdapter = HomeDiscountAdapter(context!!)
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO,
            HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))

    }


}