package com.outside.messagecenter.presenter

import com.outside.baselibrary.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.messagecenter.data.protocol.Message
import com.outside.messagecenter.presenter.view.MessageView
import com.outside.messagecenter.service.MessageService
import javax.inject.Inject

/**
 * className:    MessagePresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 17:32
 */

class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {

    @Inject
    lateinit var messageService: MessageService

    fun getMessageList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        messageService.getMessageList().excute(object : BaseObserver<MutableList<Message>?>(mView) {
            override fun onNext(t: MutableList<Message>?) {
                super.onNext(t)
                mView.onGetMessageListResult(t)
            }
        }, lifecycleProvider)

    }
}