package com.outside.paysdk.ui.activity

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.outside.baselibrary.ext.onClick
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.baselibrary.utils.YuanFenConverter
import com.outside.paysdk.R
import com.outside.paysdk.injection.component.DaggerPayComponent
import com.outside.paysdk.injection.module.PayModule
import com.outside.paysdk.presenter.PayPresenter
import com.outside.paysdk.presenter.view.PayView
import com.outside.provider.common.ProviderConstant
import com.outside.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_cash_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * className:    CashRegisterActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 9:43
 */

@Route(path = RouterPath.PaySDK.PATH_SDK)
class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView, View.OnClickListener {

    //订单号
    var mOrderId: Int = 0
    //订单总价格
    var mTotalPrice: Long = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_cash_register
    }

    override fun injectComponent() {
        DaggerPayComponent.builder().activityComponent(activityComponent).payModule(PayModule())
            .build().inject(this)
        mPresenter.mView = this

    }

    override fun initView() {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)//设置支付宝沙箱环境

        mAlipayTypeTv.isSelected = true

        mAlipayTypeTv.onClick(this)
        mWeixinTypeTv.onClick(this)
        mBankCardTypeTv.onClick(this)
        mPayBtn.onClick(this)

    }

    override fun initData() {
        super.initData()
        mOrderId = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1)
        mTotalPrice = intent.getLongExtra(ProviderConstant.KEY_ORDER_PRICE, -1)

        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)

    }

    override fun getPaySignResult(t: String) {

        doAsync {
            val resultMap: Map<String, String> = PayTask(this@CashRegisterActivity).payV2(t, true)

            uiThread {
                if (resultMap["resultStatus"].equals("9000")) {
                    mPresenter.payOrder(mOrderId)
                } else {
                    toast("支付失败：${resultMap["memo"]}")
                }
            }
        }

    }

    override fun payOrderResult(t: Boolean) {
        if (t) {
            toast("支付成功")
            finish()
        }
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.mAlipayTypeTv -> {
                updatePayType(true, false, false)
            }
            R.id.mWeixinTypeTv -> {
                updatePayType(false, true, false)
            }
            R.id.mBankCardTypeTv -> {
                updatePayType(false, true, true)
            }
            R.id.mPayBtn -> {
                //只实现了支付宝支付
                mPresenter.getPaySign(mOrderId, mTotalPrice)
            }

        }
    }


    private fun updatePayType(isAliPay: Boolean, isWeixinPay: Boolean, isBankCarPay: Boolean) {

        mAlipayTypeTv.isSelected = isAliPay
        mWeixinTypeTv.isSelected = isWeixinPay
        mBankCardTypeTv.isSelected = isBankCarPay

    }


}