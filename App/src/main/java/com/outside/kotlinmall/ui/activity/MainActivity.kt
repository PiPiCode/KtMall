package com.outside.kotlinmall.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.outside.baselibrary.ui.activity.BaseActivity
import com.outside.goodscenter.ui.fragment.CategoryFragment
import com.outside.kotlinmall.R
import com.outside.kotlinmall.ui.fragment.HomeFragment
import com.outside.kotlinmall.ui.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    private val mStack = Stack<Fragment>()
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { HomeFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMeFrament by lazy { MineFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(20)
        mBottomNavBar.checkMsgBadge(false)
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
    }

    private fun initFragment() {
        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFrament)
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer,mHomeFragment)
        manager.commit()
    }

    private fun initView() {


    }

    private fun initBottomNaviBar(){
        mBottomNavBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{
            override fun onTabUnselected(position: Int) {

            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

            override fun onTabReselected(position: Int) {

            }
        })
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer,mStack[position])
        manager.commit()
    }
}
