package com.outside.messagecenter.presenter.view

import com.outside.baselibrary.presenter.view.BaseView
import com.outside.messagecenter.data.protocol.Message

/**
 * className:    MessageView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 17:32
 */

interface MessageView:BaseView {

    fun onGetMessageListResult(t: MutableList<Message>?)
}