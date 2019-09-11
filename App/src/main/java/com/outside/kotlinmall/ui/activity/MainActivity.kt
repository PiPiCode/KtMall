package com.outside.kotlinmall.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.outside.baselibrary.rx.RxBus
import com.outside.baselibrary.rx.registerInBus
import com.outside.baselibrary.ui.activity.BaseActivity
import com.outside.baselibrary.utils.AppPrefsUtils
import com.outside.goodscenter.common.GoodsConstant
import com.outside.goodscenter.event.UpdateCartSizeEvent
import com.outside.goodscenter.ui.fragment.CartFragment
import com.outside.goodscenter.ui.fragment.CategoryFragment
import com.outside.kotlinmall.R
import com.outside.kotlinmall.ui.fragment.HomeFragment
import com.outside.kotlinmall.ui.fragment.MineFragment
import com.outside.messagecenter.ui.fragment.MessageFragment
import com.outside.provider.event.MessageBadgeEvent
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    private val mStack = Stack<Fragment>()
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMsgFragment by lazy { MessageFragment() }
    private val mMeFrament by lazy { MineFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //定时器
//        Observable.timer(2,TimeUnit.SECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { mBottomNavBar.checkMsgBadge(true) }
//
//        Observable.timer(5,TimeUnit.SECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { mBottomNavBar.checkCartBadge(0) }
        initView()
        initFragment()
        initBottomNaviBar()
        initObserve()
        loadCartSize()
    }

    private fun initFragment() {
        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFrament)
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer, mHomeFragment)
        manager.commit()
    }

    private fun initView() {

    }

    private fun initBottomNaviBar() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabUnselected(position: Int) {

            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

            override fun onTabReselected(position: Int) {

            }
        })

        mBottomNavBar.checkMsgBadge(false)
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer, mStack[position])
        manager.commit()
    }

    /*
    监听购物车数量变化
 */
    private fun initObserve() {
        RxBus.observe<UpdateCartSizeEvent>()
            .subscribe {
                loadCartSize()
            }.registerInBus(this)

        RxBus.observe<MessageBadgeEvent>()
            .subscribe { b ->
                mBottomNavBar.checkMsgBadge(b.isVisible)
            }.registerInBus(this)
    }


    private fun loadCartSize() {

        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))

    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.unRegister(this)
    }
}
