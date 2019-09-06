package com.outside.kotlinmall.ui.fragment


import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.base.ext.loadUrl
import com.kotlin.base.ext.onClick
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.activity.SettingActivity
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.kotlin.provider.common.ProviderConstant
import com.outside.baselibrary.common.BaseConstant
import com.outside.baselibrary.ui.fragment.BaseFragment
import com.outside.kotlinmall.R
import com.outside.kotlinmall.ui.adapter.HomeDiscountAdapter
import com.outside.provider.common.isLogined
import com.outside.usercenter.data.protocol.UserInfo
import com.outside.usercenter.ui.activity.LoginActivity
import com.outside.usercenter.ui.activity.UserInfoActivity
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_me.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity

/**
 * className:    HomeFragment
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/5 16:46
 */

class MineFragment : BaseFragment() , View.OnClickListener{


    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {

        if(!isLogined()){
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)

        }else{
            val urlIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)!!
            if (urlIcon.isNotEmpty()){
                mUserIconIv.loadUrl(urlIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)

        }

    }


    override fun onClick(p0: View) {
        when(p0.id){
            R.id.mUserIconIv,R.id.mUserNameTv->{
                if (isLogined()){
                    startActivity<UserInfoActivity>()
                }else{
                    startActivity<LoginActivity>()
                }
            }
            R.id.mSettingTv->{
                startActivity<SettingActivity>()
            }
        }

    }




}