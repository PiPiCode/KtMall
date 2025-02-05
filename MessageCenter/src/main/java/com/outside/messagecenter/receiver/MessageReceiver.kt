package com.outside.messagecenter.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.launcher.ARouter
import com.outside.baselibrary.rx.RxBus
import com.outside.provider.common.ProviderConstant
import com.outside.provider.event.MessageBadgeEvent
import com.outside.provider.router.RouterPath
import org.json.JSONObject

/*
    自定义Push 接收器
 */
class MessageReceiver:BroadcastReceiver() {

    val TAG = "MessageReceiver"
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        Log.d(TAG, "onReceive - " + intent.action + ", extras: " +bundle)

        when {
            JPushInterface.ACTION_REGISTRATION_ID == intent.action -> Log.d(TAG, "JPush用户注册成功")
            JPushInterface.ACTION_MESSAGE_RECEIVED == intent.action -> {
                Log.d(TAG, "接受到推送下来的自定义消息")
                RxBus.send(MessageBadgeEvent(true))

            }
            JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent.action -> Log.d(TAG, "接受到推送下来的通知")
            JPushInterface.ACTION_NOTIFICATION_OPENED == intent.action -> {
                Log.d(TAG, "用户点击打开了通知")
                bundle?.let {
                    val extra = it.getString(JPushInterface.EXTRA_EXTRA)
                    val json = JSONObject(extra)
                    val orderId = json.getInt("orderId")
                    ARouter.getInstance().build(RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
                        .withInt(ProviderConstant.KEY_ORDER_ID,orderId)
                        .navigation()
                }
            }
            else -> Log.d(TAG, "Unhandled intent - " + intent.action)
        }
    }
}
