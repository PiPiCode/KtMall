package com.outside.goodscenter.ui.activity

import android.os.Bundle
import com.outside.baselibrary.ui.activity.BaseActivity
import com.outside.goodscenter.R
import com.outside.goodscenter.ui.fragment.CartFragment
import kotlinx.android.synthetic.main.fragment_cart.*


/**
 * className:    CartActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/6 10:20
 */

class CartActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }
}