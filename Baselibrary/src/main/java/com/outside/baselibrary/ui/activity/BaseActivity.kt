package com.outside.baselibrary.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ktx.immersionBar
import com.outside.baselibrary.R

/**
 * className:    BaseActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:16
 */

open class BaseActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //沉浸式
        immersionBar {
            statusBarColor(R.color.colorPrimary)
            fitsSystemWindows(true)
        }
    }
}