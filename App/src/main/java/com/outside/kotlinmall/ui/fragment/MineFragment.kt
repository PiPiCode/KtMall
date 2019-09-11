package com.outside.kotlinmall.ui.fragment


import android.view.View

import com.outside.baselibrary.ext.loadUrl
import com.outside.baselibrary.ext.onClick
import com.outside.baselibrary.ui.fragment.BaseFragment
import com.outside.baselibrary.utils.AppPrefsUtils
import com.outside.kotlinmall.R
import com.outside.kotlinmall.ui.activity.SettingActivity
import com.outside.ordercenter.common.OrderConstant
import com.outside.ordercenter.common.OrderStatus
import com.outside.ordercenter.ui.activity.OrderActivity
import com.outside.ordercenter.ui.activity.ShipAddressActivity
import com.outside.provider.common.ProviderConstant
import com.outside.provider.common.afterLogin
import com.outside.provider.common.isLogined
import com.outside.usercenter.ui.activity.LoginActivity
import com.outside.usercenter.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * className:    HomeFragment
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/5 16:46
 */

class MineFragment : BaseFragment(), View.OnClickListener {


    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)

        mAddressTv.onClick(this)
        mSettingTv.onClick(this)
        mAllOrderTv.onClick(this)
        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {

        if (!isLogined()) {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)

        } else {
            val urlIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)!!
            if (urlIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(urlIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)

        }

    }


    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                afterLogin {
                    startActivity<UserInfoActivity>()
                }
            }

            R.id.mAddressTv -> {
                afterLogin {
                    startActivity<ShipAddressActivity>()
                }
            }

            R.id.mAllOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }

            R.id.mWaitPayOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
                }
            }
            R.id.mWaitConfirmOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
                }
            }

            R.id.mCompleteOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
                }
            }

            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }
    }

}