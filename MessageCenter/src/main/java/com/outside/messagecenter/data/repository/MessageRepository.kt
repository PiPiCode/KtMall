package com.outside.messagecenter.data.repository


import javax.inject.Inject

import com.outside.baselibrary.data.net.RetrofitFactory
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.messagecenter.data.api.MessageApi
import com.outside.messagecenter.data.protocol.Message
import io.reactivex.Observable


/*
   消息数据层
 */
class MessageRepository @Inject constructor() {

    /*
        获取消息列表
     */
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>> {
        return RetrofitFactory.instance.create(MessageApi::class.java).getMessageList()
    }



}
