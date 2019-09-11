package com.outside.messagecenter.provider

import android.content.Context
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.facade.annotation.Route
import com.outside.provider.PushProvider
import com.outside.provider.router.RouterPath

/**
 * className:    PushProviderImpl
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 16:41
 */
@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
class PushProviderImpl:PushProvider {

    private var mContext:Context? = null

    override fun getPushId(): String {
        return  JPushInterface.getRegistrationID(mContext)
    }

    override fun init(context: Context?) {
        mContext = context
    }
}