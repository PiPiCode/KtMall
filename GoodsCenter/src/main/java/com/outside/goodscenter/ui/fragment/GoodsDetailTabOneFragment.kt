package com.outside.goodscenter.ui.fragment

import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.outside.baselibrary.ext.onClick
import com.outside.baselibrary.rx.RxBus
import com.outside.baselibrary.rx.registerInBus
import com.outside.baselibrary.ui.activity.BaseActivity
import com.outside.baselibrary.ui.fragment.BaseMvpFragment
import com.outside.baselibrary.utils.BannerImageLoader
import com.outside.baselibrary.utils.YuanFenConverter
import com.outside.goodscenter.R
import com.outside.goodscenter.common.GoodsConstant
import com.outside.goodscenter.data.protocol.Goods
import com.outside.goodscenter.event.AddCartEvent
import com.outside.goodscenter.event.GoodsDetailImageEvent
import com.outside.goodscenter.event.SkuChangedEvent
import com.outside.goodscenter.event.UpdateCartSizeEvent
import com.outside.goodscenter.injection.component.DaggerGoodsComponent
import com.outside.goodscenter.injection.module.GoodsModule
import com.outside.goodscenter.presenter.GoodsDetailPresenter
import com.outside.goodscenter.presenter.view.GoodsDetailView
import com.outside.goodscenter.ui.activity.GoodsDetailActivity
import com.outside.goodscenter.widget.GoodsSkuPopView


import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/*
    商品详情Tab One
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {
    override fun getLayoutId(): Int {
        return R.layout.fragment_goods_detail_tab_one
    }

    private lateinit var mSkuPop: GoodsSkuPopView
    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods: Goods? = null


    override fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        //sku弹层
        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView
                , Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                0, 0
            )
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }

    /*
      初始化缩放动画
   */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    /*
        初始化sku弹层
     */
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity!!)
        mSkuPop.setOnDismissListener{
            (activity as BaseActivity).contentView?.startAnimation(mAnimationEnd)
        }
    }

    /*
        加载数据
     */
    private fun loadData() {
        activity?.let {
            mPresenter.getGoodsDetail(it.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
        }
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    /*
        获取商品详情 回调
     */
    override fun onGetGoodsDetailResult(result: Goods) {

        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        RxBus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)
    }

    /*
        加载SKU数据
     */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)

    }

    /*
        监听SKU变化及加入购物车事件
     */
    private fun initObserve(){
        RxBus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() +GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount()+"件"
                }.registerInBus(this)

        RxBus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }

    /*
        取消事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        RxBus.unRegister(this)
    }

    /*
        加入购物车
     */
    private fun addCart(){
        mCurGoods?.let {
            mPresenter.addCart(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mSkuPop.getSelectCount(),
                    mSkuPop.getSelectSku()
                    )
        }

    }

    /*
        加入购物车 回调
     */
    override fun onAddCartResult(result: Int) {
        RxBus.send(UpdateCartSizeEvent())
    }

}
