package com.outside.messagecenter.service

import com.outside.messagecenter.data.protocol.Message
import io.reactivex.Observable

/**
 * className:    MessageService
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 17:33
 */

interface MessageService {

    fun getMessageList(): Observable<MutableList<Message>?>
}